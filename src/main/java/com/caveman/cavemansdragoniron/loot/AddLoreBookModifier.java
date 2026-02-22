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

/**
 * Global loot modifier that adds a lore book (written book with fixed content) to the rolled loot.
 * The "book" field is the book id from {@link ModBooks}.
 * Optional "chance" (0.0–1.0) rolls whether to add the book; if omitted, the book is always added.
 */
public class AddLoreBookModifier extends LootModifier {

    public static final MapCodec<AddLoreBookModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    inst.group(
                            com.mojang.serialization.Codec.STRING.fieldOf("book").forGetter(e -> e.bookId),
                            com.mojang.serialization.Codec.FLOAT.optionalFieldOf("chance", 1.0F).forGetter(e -> e.chance)
                    )
            ).apply(inst, AddLoreBookModifier::new));

    private final String bookId;
    private final float chance;

    public AddLoreBookModifier(LootItemCondition[] conditionsIn, String bookId) {
        this(conditionsIn, bookId, 1.0F);
    }

    public AddLoreBookModifier(LootItemCondition[] conditionsIn, String bookId, float chance) {
        super(conditionsIn);
        this.bookId = bookId;
        this.chance = chance;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(lootContext)) {
                return generatedLoot;
            }
        }
        if (chance < 1.0F && lootContext.getRandom().nextFloat() >= chance) {
            return generatedLoot;
        }
        ItemStack bookStack = ModBooks.getStack(bookId);
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
