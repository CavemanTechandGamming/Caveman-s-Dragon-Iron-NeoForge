package com.caveman.cavemansdragoniron.loot;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

/**
 * Global loot modifier that adds an enchanted book (Chunk Eater I) with a fixed chance.
 * Used for End City treasure and fishing to match Mending-style rarity.
 */
public class AddChunkEaterBookModifier extends LootModifier {

    public static final MapCodec<AddChunkEaterBookModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    com.mojang.serialization.Codec.FLOAT.fieldOf("chance").forGetter(m -> m.chance)
            ).apply(inst, AddChunkEaterBookModifier::new));

    private final float chance;

    public AddChunkEaterBookModifier(LootItemCondition[] conditionsIn, float chance) {
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
        var chunkEaterHolder = enchantmentRegistry.getHolder(ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, "chunk_eater"));
        if (chunkEaterHolder.isEmpty()) {
            return generatedLoot;
        }
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        mutable.set(chunkEaterHolder.get(), 1);
        ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
        book.set(DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable());
        generatedLoot.add(book);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
