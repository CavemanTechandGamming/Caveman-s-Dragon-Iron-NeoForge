package com.caveman.cavemansdragoniron.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

/**
 * Global loot modifier that adds an item with a fixed chance and a random stack size between min and max (inclusive).
 */
public class AddItemWithChanceAndCountModifier extends LootModifier {

    public static final MapCodec<AddItemWithChanceAndCountModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    inst.group(
                            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item),
                            com.mojang.serialization.Codec.FLOAT.fieldOf("chance").forGetter(e -> e.chance),
                            com.mojang.serialization.Codec.INT.fieldOf("min_count").forGetter(e -> e.minCount),
                            com.mojang.serialization.Codec.INT.fieldOf("max_count").forGetter(e -> e.maxCount)
                    )
            ).apply(inst, AddItemWithChanceAndCountModifier::new));

    private final Item item;
    private final float chance;
    private final int minCount;
    private final int maxCount;

    public AddItemWithChanceAndCountModifier(LootItemCondition[] conditionsIn, Item item, float chance, int minCount, int maxCount) {
        super(conditionsIn);
        this.item = item;
        this.chance = chance;
        this.minCount = Mth.clamp(minCount, 1, 64);
        this.maxCount = Mth.clamp(maxCount, 1, 64);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) {
                return generatedLoot;
            }
        }
        if (lootContext.getRandom().nextFloat() < chance) {
            int count = minCount >= maxCount ? minCount : lootContext.getRandom().nextInt(maxCount - minCount + 1) + minCount;
            generatedLoot.add(new ItemStack(item, count));
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
