package com.caveman.cavemansdragoniron.datagen;

import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.caveman.cavemansdragoniron.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAGON_IRON_INGOT.get())
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', ModItems.DRAGON_IRON_NUGGET.get())
                .unlockedBy("has_dragon_iron_nugget", has(ModItems.DRAGON_IRON_NUGGET))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_ingot_from_nuggets");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAGON_IRON_INGOT.get(), 4)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("CAC")
                .define('A', Items.OBSIDIAN)
                .define('B', Items.DRAGON_BREATH)
                .define('C', Items.IRON_INGOT)
                .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_ingot_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DRAGON_IRON_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.DRAGON_IRON_INGOT.get())
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_block_from_ingots");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DRAGON_IRON_NUGGET.get(), 9)
                .requires(ModItems.DRAGON_IRON_INGOT)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_nuggets_from_ingot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DRAGON_IRON_INGOT.get(), 9)
                .requires(ModBlocks.DRAGON_IRON_BLOCK)
                .unlockedBy("has_dragon_iron_block", has(ModBlocks.DRAGON_IRON_BLOCK))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_ingots_from_block");

        stairBuilder(ModBlocks.DRAGON_IRON_STAIRS.get(), Ingredient.of(ModBlocks.DRAGON_IRON_BLOCK))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_block", has(ModBlocks.DRAGON_IRON_BLOCK))
                .save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRAGON_IRON_SLAB.get(), ModBlocks.DRAGON_IRON_BLOCK.get());

        buttonBuilder(ModBlocks.DRAGON_IRON_BUTTON.get(), Ingredient.of(ModItems.DRAGON_IRON_NUGGET.get()))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_nugget", has(ModItems.DRAGON_IRON_NUGGET.get()))
                .save(recipeOutput);

        pressurePlate(recipeOutput, ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get(), ModItems.DRAGON_IRON_NUGGET.get());

        fenceBuilder(ModBlocks.DRAGON_IRON_FENCE.get(), Ingredient.of(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_block", has(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .save(recipeOutput);

        fenceGateBuilder(ModBlocks.DRAGON_IRON_FENCE_GATE.get(), Ingredient.of(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_block", has(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .save(recipeOutput);

        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRAGON_IRON_WALL.get(), ModBlocks.DRAGON_IRON_BLOCK.get());

        doorBuilder(ModBlocks.DRAGON_IRON_DOOR.get(), Ingredient.of(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_block", has(ModBlocks.DRAGON_IRON_BLOCK.get()))
                .save(recipeOutput);

        trapdoorBuilder(ModBlocks.DRAGON_IRON_TRAP_DOOR.get(), Ingredient.of(ModItems.DRAGON_IRON_INGOT.get()))
                .group("dragon_iron_block")
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_axe_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_HAMMER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern(" B ")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_hammer_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_pickaxe_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_HOE.get())
                .pattern("AA")
                .pattern(" B")
                .pattern(" B")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_hoe_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_SHOVEL.get())
                .pattern("A")
                .pattern("B")
                .pattern("B")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_shovel_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DRAGON_IRON_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("B")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_sword_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DRAGON_IRON_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("   ")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_helmet_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DRAGON_IRON_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_chestplate_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DRAGON_IRON_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_leggings_from_scratch");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DRAGON_IRON_BOOTS.get())
                .pattern("   ")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.DRAGON_IRON_INGOT)
                .unlockedBy("has_dragon_iron_ingot", has(ModItems.DRAGON_IRON_INGOT))
                .save(recipeOutput, "cavemansdragoniron:dragon_iron_boots_from_scratch");

    }
}
