package com.caveman.cavemansdragoniron.datagen;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.caveman.cavemansdragoniron.item.ModItems;
import com.caveman.cavemansdragoniron.loot.AddChunkEaterBookModifier;
import com.caveman.cavemansdragoniron.loot.AddEnchantedDragonIronGearModifier;
import com.caveman.cavemansdragoniron.loot.AddItemWithChanceAndCountModifier;
import com.caveman.cavemansdragoniron.loot.AddItemWithChanceModifier;
import com.caveman.cavemansdragoniron.loot.AddItemWithConfigChanceModifier;
import com.caveman.cavemansdragoniron.loot.AddLoreBookModifier;
import com.caveman.cavemansdragoniron.loot.AddRandomLoreBookModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
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

        // Lore books: at most one lore book per chest roll, selected from Volumes 1–5.
        // 0.34 keeps overall drop chance close to the old setup of 5 independent 0.08 rolls.
        float loreBookChance = 0.34F;
        List<String> loreVolumes = List.of(
                "volume_1_the_fragment",
                "volume_2_the_smith_who_failed",
                "volume_3_the_eyes_that_open",
                "volume_4_the_dragon_and_the_flame",
                "volume_5_the_first_dragon_smith"
        );

        addRandomLoreBook("random_lore_book_end_city_treasure", "chests/end_city_treasure", loreVolumes, loreBookChance);
        addRandomLoreBook("random_lore_book_simple_dungeon", "chests/simple_dungeon", loreVolumes, loreBookChance);
        addRandomLoreBook("random_lore_book_abandoned_mineshaft", "chests/abandoned_mineshaft", loreVolumes, loreBookChance);
        addRandomLoreBook("random_lore_book_village_weaponsmith", "chests/village/village_weaponsmith", loreVolumes, loreBookChance);
        addRandomLoreBook("random_lore_book_village_toolsmith", "chests/village/village_toolsmith", loreVolumes, loreBookChance);
        addRandomLoreBook("random_lore_book_village_library", "chests/village/village_library", loreVolumes, loreBookChance);

        // Epilogue only in village weaponsmith, toolsmith, and armorer chests.
        addLoreBook("lore_book_epilogue_village_weaponsmith", "chests/village/village_weaponsmith", "epilogue_the_flame_that_remains", 0.08F);
        addLoreBook("lore_book_epilogue_village_toolsmith", "chests/village/village_toolsmith", "epilogue_the_flame_that_remains", 0.08F);
        addLoreBook("lore_book_epilogue_village_armorer", "chests/village/village_armorer", "epilogue_the_flame_that_remains", 0.08F);

        // ===== End City treasure: Dragon Iron remnants (ancient builders) — kept rare so each find feels special =====
        LootItemCondition[] endCityTreasure = new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build()
        };

        this.add("dragon_iron_nugget_from_end_city_treasure",
                new AddItemWithChanceAndCountModifier(endCityTreasure, ModItems.DRAGON_IRON_NUGGET.get(), 0.08F, 1, 3));
        this.add("dragon_iron_ingot_from_end_city_treasure",
                new AddItemWithChanceModifier(endCityTreasure, ModItems.DRAGON_IRON_INGOT.get(), 0.04F));
        this.add("dragon_iron_furnace_from_end_city_treasure",
                new AddItemWithChanceModifier(endCityTreasure, ModBlocks.DRAGON_IRON_FURNACE.get().asItem(), 0.01F));
        this.add("dragon_iron_enchanted_gear_from_end_city_treasure",
                new AddEnchantedDragonIronGearModifier(endCityTreasure, 0.05F));

        // Chunk Eater book: same rarity tier as Mending — End City and fishing
        this.add("chunk_eater_book_from_end_city_treasure",
                new AddChunkEaterBookModifier(endCityTreasure, 0.035F));
        this.add("chunk_eater_book_from_fishing",
                new AddChunkEaterBookModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("gameplay/fishing")).build()
                }, 0.01F));
    }

    private void addRandomLoreBook(String modifierName, String lootTablePath, List<String> bookIds, float chance) {
        this.add(modifierName,
                new AddRandomLoreBookModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(lootTablePath)).build()
                }, bookIds, chance));
    }

    private void addLoreBook(String modifierName, String lootTablePath, String bookId, float chance) {
        this.add(modifierName,
                new AddLoreBookModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(lootTablePath)).build()
                }, bookId, chance));
    }
}