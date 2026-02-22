package com.caveman.cavemansdragoniron.datagen;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.item.ModItems;
import com.caveman.cavemansdragoniron.loot.AddItemWithConfigChanceModifier;
import com.caveman.cavemansdragoniron.loot.AddLoreBookModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CavemansDragonIron.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("dragon_iron_nugget_from_enderman",
                        new AddItemWithConfigChanceModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("entities/enderman")).build()
                        }, ModItems.DRAGON_IRON_NUGGET.get()));

        // Lore books: 8% chance. Volumes 1–5 in all chest types; Epilogue only in village weaponsmith/toolsmith.
        float loreBookChance = 0.08F;
        String[] chestSuffixes = {
                "end_city_treasure", "chests/end_city_treasure",
                "simple_dungeon", "chests/simple_dungeon",
                "abandoned_mineshaft", "chests/abandoned_mineshaft",
                "village_weaponsmith", "chests/village/village_weaponsmith",
                "village_toolsmith", "chests/village/village_toolsmith",
                "village_library", "chests/village/village_library"
        };

        addLoreBookToAllChests("volume_1", "volume_1_the_fragment", loreBookChance, chestSuffixes);
        addLoreBookToAllChests("volume_2", "volume_2_the_smith_who_failed", loreBookChance, chestSuffixes);
        addLoreBookToAllChests("volume_3", "volume_3_the_eyes_that_open", loreBookChance, chestSuffixes);
        addLoreBookToAllChests("volume_4", "volume_4_the_dragon_and_the_flame", loreBookChance, chestSuffixes);
        addLoreBookToAllChests("volume_5", "volume_5_the_first_dragon_smith", loreBookChance, chestSuffixes);

        // Epilogue only in village weaponsmith and toolsmith
        addLoreBook("lore_book_epilogue_village_weaponsmith", "chests/village/village_weaponsmith", "epilogue_the_flame_that_remains", loreBookChance);
        addLoreBook("lore_book_epilogue_village_toolsmith", "chests/village/village_toolsmith", "epilogue_the_flame_that_remains", loreBookChance);
    }

    private void addLoreBookToAllChests(String bookSuffix, String bookId, float chance, String[] chestSuffixes) {
        for (int i = 0; i < chestSuffixes.length; i += 2) {
            String modifierSuffix = chestSuffixes[i];
            String lootTablePath = chestSuffixes[i + 1];
            addLoreBook("lore_book_" + bookSuffix + "_" + modifierSuffix, lootTablePath, bookId, chance);
        }
    }

    private void addLoreBook(String modifierName, String lootTablePath, String bookId, float chance) {
        this.add(modifierName,
                new AddLoreBookModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(lootTablePath)).build()
                }, bookId, chance));
    }
}