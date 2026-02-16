package com.caveman.cavemansdragoniron.event;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.caveman.cavemansdragoniron.item.ModItems;
import com.caveman.cavemansdragoniron.item.custom.HammerItem;
import com.caveman.cavemansdragoniron.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import org.checkerframework.checker.units.qual.C;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = CavemansDragonIron.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if (pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
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
