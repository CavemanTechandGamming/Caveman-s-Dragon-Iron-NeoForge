package com.caveman.cavemansdragoniron.event;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.caveman.cavemansdragoniron.item.ModItems;
import com.caveman.cavemansdragoniron.item.custom.HammerItem;
import com.caveman.cavemansdragoniron.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerDestroyItemEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = CavemansDragonIron.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    /** Pending Chunk Eater magnetic pulls: items in layerBox get pulled toward player until expireTick. */
    private static final List<ChunkEaterPull> PENDING_CHUNK_EATER_PULLS = new CopyOnWriteArrayList<>();

    private record ChunkEaterPull(ResourceKey<Level> dimension, AABB layerBox, UUID playerId, int expireTick) {}

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        BlockPos initialBlockPos = event.getPos();
        if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
            return;
        }

        // Chunk Eater: one 16×16 chunk-aligned layer, only when mining straight up or straight down
        if (player instanceof ServerPlayer serverPlayer) {
            var enchantmentRegistry = event.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            var chunkEaterHolder = enchantmentRegistry.getHolder(ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, "chunk_eater"));
            if (chunkEaterHolder.isPresent() && EnchantmentHelper.getItemEnchantmentLevel(chunkEaterHolder.get(), mainHandItem) >= 1) {
                Direction hitFace = getHitDirection(player);
                if (hitFace == Direction.UP || hitFace == Direction.DOWN) {
                    int layerY = initialBlockPos.getY(); // same horizontal layer as the block being mined
                    List<BlockPos> layer = getChunkAlignedLayer(initialBlockPos.getX(), initialBlockPos.getZ(), layerY);
                    var level = event.getLevel();
                    var item = mainHandItem.getItem();
                    for (BlockPos pos : layer) {
                        if (level.getWorldBorder().isWithinBounds(pos) && (item instanceof DiggerItem digger && digger.isCorrectToolForDrops(mainHandItem, level.getBlockState(pos)))) {
                            HARVESTED_BLOCKS.add(pos);
                            serverPlayer.gameMode.destroyBlock(pos);
                            HARVESTED_BLOCKS.remove(pos);
                        }
                    }
                    // Magnetic pull: schedule pulls for the next few ticks (drops may spawn after this event)
                    if (level instanceof ServerLevel serverLevel) {
                        int baseX = (initialBlockPos.getX() >> 4) * 16;
                        int baseZ = (initialBlockPos.getZ() >> 4) * 16;
                        AABB layerBox = new AABB(baseX, layerY - 0.5, baseZ, baseX + 16, layerY + 1.5, baseZ + 16);
                        int currentTick = serverLevel.getServer().getTickCount();
                        int expireTick = currentTick + 12; // pull for 12 ticks (~0.6 sec) so all drops are caught
                        PENDING_CHUNK_EATER_PULLS.add(new ChunkEaterPull(serverLevel.dimension(), layerBox, serverPlayer.getUUID(), expireTick));
                    }
                    return;
                }
            }
        }

        // Hammer: 3×3 in the plane of the hit face
        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos.equals(initialBlockPos) || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        int currentTick = serverLevel.getServer().getTickCount();
        ResourceKey<Level> dimension = serverLevel.dimension();
        PENDING_CHUNK_EATER_PULLS.removeIf(pull -> pull.dimension().equals(dimension) && currentTick > pull.expireTick());
        for (ChunkEaterPull pull : PENDING_CHUNK_EATER_PULLS) {
            if (!pull.dimension().equals(dimension)) {
                continue;
            }
            ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(pull.playerId());
            if (player == null) {
                continue;
            }
            Vec3 playerCenter = player.position();
            double pullSpeed = 0.35;
            for (ItemEntity itemEntity : serverLevel.getEntitiesOfClass(ItemEntity.class, pull.layerBox())) {
                Vec3 toPlayer = playerCenter.subtract(itemEntity.position()).normalize();
                itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add(toPlayer.scale(pullSpeed)));
                itemEntity.setPickUpDelay(0);
            }
            for (ExperienceOrb orb : serverLevel.getEntitiesOfClass(ExperienceOrb.class, pull.layerBox())) {
                Vec3 toPlayer = playerCenter.subtract(orb.position()).normalize();
                orb.setDeltaMovement(orb.getDeltaMovement().add(toPlayer.scale(pullSpeed)));
            }
        }
    }

    /** Dragon iron tools, weapons, and armor never break; they stop at 1 durability.
     * PlayerDestroyItemEvent is not cancellable in NeoForge, so we give the item back at 1 durability. */
    @SubscribeEvent
    public static void onPlayerDestroyItem(PlayerDestroyItemEvent event) {
        ItemStack original = event.getOriginal();
        if (original.isEmpty() || !isDragonIronItem(original)) {
            return;
        }
        var hand = event.getHand();
        if (hand == null) {
            return; // armor/crafting path; hand is null, slot not easily identified
        }
        int maxDamage = original.getMaxDamage();
        if (maxDamage <= 0) {
            return;
        }
        ItemStack fixed = original.copy();
        fixed.set(DataComponents.DAMAGE, maxDamage - 1);
        event.getEntity().setItemInHand(hand, fixed);
    }

    private static boolean isDragonIronItem(ItemStack stack) {
        return stack.is(ModItems.DRAGON_IRON_SWORD.get())
                || stack.is(ModItems.DRAGON_IRON_PICKAXE.get())
                || stack.is(ModItems.DRAGON_IRON_SHOVEL.get())
                || stack.is(ModItems.DRAGON_IRON_AXE.get())
                || stack.is(ModItems.DRAGON_IRON_HOE.get())
                || stack.is(ModItems.DRAGON_IRON_HAMMER.get())
                || stack.is(ModItems.DRAGON_IRON_HELMET.get())
                || stack.is(ModItems.DRAGON_IRON_CHESTPLATE.get())
                || stack.is(ModItems.DRAGON_IRON_LEGGINGS.get())
                || stack.is(ModItems.DRAGON_IRON_BOOTS.get());
    }

    /** Raycast from player to get the block face being hit (same as Hammer). */
    private static Direction getHitDirection(Player player) {
        BlockHitResult hit = player.level().clip(new ClipContext(
                player.getEyePosition(1f),
                player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f)),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        return hit.getType() == HitResult.Type.MISS ? null : hit.getDirection();
    }

    /** One 16×16 horizontal layer aligned to chunk borders at the given Y. */
    private static List<BlockPos> getChunkAlignedLayer(int blockX, int blockZ, int layerY) {
        int baseX = (blockX >> 4) * 16;
        int baseZ = (blockZ >> 4) * 16;
        List<BlockPos> list = new ArrayList<>(256);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                list.add(new BlockPos(baseX + x, layerY, baseZ + z));
            }
        }
        return list;
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.DRAGON_SMITH.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // =====================================================
            // DRAGONSMITH VILLAGER TRADES — FULL BALANCED SET
            // =====================================================


            // =====================================================
            // LEVEL 1 — NOVICE
            // Raw material access
            // =====================================================

            // Dragon Iron Nugget
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    new ItemStack(ModItems.DRAGON_IRON_NUGGET.get(), 6),
                    16, 2, 0.02f));

            // Dragon Iron Block
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 40),
                    new ItemStack(ModBlocks.DRAGON_IRON_BLOCK.get(), 1),
                    4, 5, 0.05f));


            // =====================================================
            // LEVEL 2 — APPRENTICE
            // Structural and crafting materials
            // =====================================================

            // Dragon Iron Ingot
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.DRAGON_IRON_INGOT.get(), 1),
                    12, 10, 0.05f));

            // Dragon Iron Slab
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModBlocks.DRAGON_IRON_SLAB.get(), 2),
                    10, 8, 0.05f));

            // Dragon Iron Stairs
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 15),
                    new ItemStack(ModBlocks.DRAGON_IRON_STAIRS.get(), 2),
                    8, 10, 0.05f));

            // Dragon Iron Fence
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModBlocks.DRAGON_IRON_FENCE.get(), 2),
                    8, 10, 0.05f));

            // Dragon Iron Fence Gate
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 18),
                    new ItemStack(ModBlocks.DRAGON_IRON_FENCE_GATE.get(), 1),
                    6, 12, 0.05f));

            // Dragon Iron Wall
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModBlocks.DRAGON_IRON_WALL.get(), 2),
                    8, 10, 0.05f));


            // =====================================================
            // LEVEL 3 — JOURNEYMAN
            // Functional and redstone components
            // =====================================================

            // Dragon Iron Door
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModBlocks.DRAGON_IRON_DOOR.get(), 1),
                    6, 15, 0.05f));

            // Dragon Iron Trapdoor
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 9),
                    new ItemStack(ModBlocks.DRAGON_IRON_TRAP_DOOR.get(), 1),
                    6, 15, 0.05f));

            // Dragon Iron Pressure Plate
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get(), 1),
                    8, 12, 0.05f));

            // Dragon Iron Button
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 4),
                    new ItemStack(ModBlocks.DRAGON_IRON_BUTTON.get(), 1),
                    12, 8, 0.05f));


            // =====================================================
            // LEVEL 4 — EXPERT
            // Full Dragon Iron Armor Set
            // =====================================================

            // Helmet
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 35),
                    new ItemStack(ModItems.DRAGON_IRON_HELMET.get(), 1),
                    3, 30, 0.10f));

            // Chestplate
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 50),
                    new ItemStack(ModItems.DRAGON_IRON_CHESTPLATE.get(), 1),
                    3, 30, 0.10f));

            // Leggings
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 45),
                    new ItemStack(ModItems.DRAGON_IRON_LEGGINGS.get(), 1),
                    3, 30, 0.10f));

            // Boots
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 30),
                    new ItemStack(ModItems.DRAGON_IRON_BOOTS.get(), 1),
                    3, 30, 0.10f));


            // =====================================================
            // LEVEL 5 — MASTER
            // Full Dragon Iron Tool Set
            // =====================================================

            // Sword
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 40),
                    new ItemStack(ModItems.DRAGON_IRON_SWORD.get(), 1),
                    2, 30, 0.15f));

            // Pickaxe
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 45),
                    new ItemStack(ModItems.DRAGON_IRON_PICKAXE.get(), 1),
                    2, 30, 0.15f));

            // Axe
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 45),
                    new ItemStack(ModItems.DRAGON_IRON_AXE.get(), 1),
                    2, 30, 0.15f));

            // Shovel
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 25),
                    new ItemStack(ModItems.DRAGON_IRON_SHOVEL.get(), 1),
                    3, 30, 0.15f));

            // Hoe
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 20),
                    new ItemStack(ModItems.DRAGON_IRON_HOE.get(), 1),
                    3, 30, 0.15f));

            // Hammer
            trades.get(5).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 50),
                    new ItemStack(ModItems.DRAGON_IRON_HAMMER.get(), 1),
                    2, 30, 0.15f));

        }

        // Librarian: Chunk Eater book as a possible level-1 trade (5% chance so it's rare like Mending).
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((entity, randomSource) -> {
                if (randomSource.nextFloat() >= 0.05f) { // Change 0.05f to adjust chance (e.g. 0.10f = 10%, 0.02f = 2%)
                    return null; // No trade this time — keeps it a possibility, not guaranteed
                }
                // Can't use a fixed ItemStack like Farmer's goji berries: the book is ENCHANTED_BOOK + our enchant from the registry (only available at runtime).
                var registryAccess = entity.level().registryAccess();
                var enchantmentRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);
                var chunkEaterHolder = enchantmentRegistry.getHolder(ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, "chunk_eater"));
                if (chunkEaterHolder.isEmpty()) {
                    return null;
                }
                ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutable.set(chunkEaterHolder.get(), 1);
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                book.set(DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable());
                return new MerchantOffer(
                        new ItemCost(Items.EMERALD, 20),
                        Optional.of(new ItemCost(Items.BOOK, 1)),
                        book,
                        12, 2, 0.05f);
            });
        }
    }


    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        // ==========================
        // Wandering Trader - Common Trades
        // These are useful but not progression-breaking.
        // More expensive than villager equivalents.
        // ==========================

        genericTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 8),
                new ItemStack(ModItems.DRAGON_IRON_NUGGET.get(), 8),
                8, 10, 0.2f));

        genericTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 36),
                new ItemStack(ModItems.DRAGON_IRON_SHOVEL.get(), 1),
                1, 10, 0.2f));

        genericTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 42),
                new ItemStack(ModItems.DRAGON_IRON_HOE.get(), 1),
                1, 10, 0.2f));

        genericTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 64),
                new ItemStack(ModItems.DRAGON_IRON_INGOT.get(), 4),
                1, 10, 0.2f));


        // ==========================
        // Wandering Trader - Rare Trades
        // These should ALWAYS be more expensive than villager Master trades.
        // Premium convenience pricing.
        // ==========================

        rareTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 80),
                new ItemStack(ModItems.DRAGON_IRON_HAMMER.get(), 1),
                1, 10, 0.2f));

        rareTrades.add((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 72),
                new ItemStack(ModBlocks.DRAGON_IRON_BLOCK.get(), 1),
                1, 10, 0.2f));

    }
}
