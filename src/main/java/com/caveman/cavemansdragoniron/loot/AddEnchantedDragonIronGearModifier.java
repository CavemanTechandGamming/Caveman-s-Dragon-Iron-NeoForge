package com.caveman.cavemansdragoniron.loot;

import com.caveman.cavemansdragoniron.item.ModItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.stream.Stream;

/**
 * Global loot modifier that adds one random Dragon Iron tool, weapon, or armor piece
 * with random enchantments. Tier (decent / good / perfect) controls enchantment level range.
 */
public class AddEnchantedDragonIronGearModifier extends LootModifier {

    private static final List<Item> GEAR_ITEMS = List.of(
            ModItems.DRAGON_IRON_SWORD.get(),
            ModItems.DRAGON_IRON_PICKAXE.get(),
            ModItems.DRAGON_IRON_AXE.get(),
            ModItems.DRAGON_IRON_SHOVEL.get(),
            ModItems.DRAGON_IRON_HOE.get(),
            ModItems.DRAGON_IRON_HAMMER.get(),
            ModItems.DRAGON_IRON_HELMET.get(),
            ModItems.DRAGON_IRON_CHESTPLATE.get(),
            ModItems.DRAGON_IRON_LEGGINGS.get(),
            ModItems.DRAGON_IRON_BOOTS.get()
    );

    /** Chance to add any piece of gear (0.0–1.0). */
    public static final MapCodec<AddEnchantedDragonIronGearModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    com.mojang.serialization.Codec.FLOAT.fieldOf("chance").forGetter(e -> e.chance)
            ).apply(inst, AddEnchantedDragonIronGearModifier::new));

    private final float chance;

    public AddEnchantedDragonIronGearModifier(LootItemCondition[] conditionsIn, float chance) {
        super(conditionsIn);
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) {
                return generatedLoot;
            }
        }
        if (lootContext.getRandom().nextFloat() >= chance) {
            return generatedLoot;
        }
        if (lootContext.getLevel() == null) {
            return generatedLoot;
        }
        var registryAccess = lootContext.getLevel().registryAccess();
        var enchantmentRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);
        Item gearItem = GEAR_ITEMS.get(lootContext.getRandom().nextInt(GEAR_ITEMS.size()));
        ItemStack stack = new ItemStack(gearItem);
        if (!EnchantmentHelper.canStoreEnchantments(stack)) {
            return generatedLoot;
        }
        var random = lootContext.getRandom();
        int enchantLevel = rollEnchantLevel(random);
        Stream<Holder<Enchantment>> enchantmentHolders =
                enchantmentRegistry.holders().map(h -> (Holder<Enchantment>) h);
        ItemStack enchanted = EnchantmentHelper.enchantItem(
                random,
                stack,
                enchantLevel,
                enchantmentHolders
        );
        if (!enchanted.isEmpty()) {
            generatedLoot.add(enchanted);
        }
        return generatedLoot;
    }

    /** Rolls tier: decent 55%, good 38%, perfect 7%. Returns enchantment level for that tier. */
    private static int rollEnchantLevel(net.minecraft.util.RandomSource random) {
        float roll = random.nextFloat();
        if (roll < 0.55F) {
            return 5 + random.nextInt(8);
        }
        if (roll < 0.93F) {
            return 15 + random.nextInt(11);
        }
        return 28 + random.nextInt(3);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
