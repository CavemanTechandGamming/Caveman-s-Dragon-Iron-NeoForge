package com.caveman.cavemansdragoniron.loot;

import com.caveman.cavemansdragoniron.book.ModBooks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;

/**
 * Global loot modifier that adds at most one random lore book from a configured list.
 */
public class AddRandomLoreBookModifier extends LootModifier {

    public static final MapCodec<AddRandomLoreBookModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    inst.group(
                            com.mojang.serialization.Codec.list(com.mojang.serialization.Codec.STRING)
                                    .fieldOf("books")
                                    .forGetter(e -> e.bookIds),
                            com.mojang.serialization.Codec.FLOAT.optionalFieldOf("chance", 1.0F)
                                    .forGetter(e -> e.chance)
                    )
            ).apply(inst, AddRandomLoreBookModifier::new));

    private final List<String> bookIds;
    private final float chance;

    public AddRandomLoreBookModifier(LootItemCondition[] conditionsIn, List<String> bookIds, float chance) {
        super(conditionsIn);
        this.bookIds = bookIds;
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) {
                return generatedLoot;
            }
        }
        if (bookIds.isEmpty()) {
            return generatedLoot;
        }
        if (chance < 1.0F && lootContext.getRandom().nextFloat() >= chance) {
            return generatedLoot;
        }

        int pick = lootContext.getRandom().nextInt(bookIds.size());
        ItemStack bookStack = ModBooks.getStack(bookIds.get(pick));
        if (!bookStack.isEmpty()) {
            generatedLoot.add(bookStack);
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
