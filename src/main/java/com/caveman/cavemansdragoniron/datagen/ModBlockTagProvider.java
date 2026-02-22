package com.caveman.cavemansdragoniron.datagen;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.caveman.cavemansdragoniron.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CavemansDragonIron.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.FANCY_DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_PANE.get())
                .add(ModBlocks.DRAGON_IRON_BUTTON.get())
                .add(ModBlocks.DRAGON_IRON_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_FENCE.get())
                .add(ModBlocks.DRAGON_IRON_FENCE_GATE.get())
                .add(ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get())
                .add(ModBlocks.DRAGON_IRON_SLAB.get())
                .add(ModBlocks.DRAGON_IRON_STAIRS.get())
                .add(ModBlocks.DRAGON_IRON_TRAP_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_WALL.get())
                .add(ModBlocks.DRAGON_IRON_FURNACE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.FANCY_DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_PANE.get())
                .add(ModBlocks.DRAGON_IRON_BUTTON.get())
                .add(ModBlocks.DRAGON_IRON_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_FENCE.get())
                .add(ModBlocks.DRAGON_IRON_FENCE_GATE.get())
                .add(ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get())
                .add(ModBlocks.DRAGON_IRON_SLAB.get())
                .add(ModBlocks.DRAGON_IRON_STAIRS.get())
                .add(ModBlocks.DRAGON_IRON_TRAP_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_WALL.get())
                .add(ModBlocks.DRAGON_IRON_FURNACE.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.FANCY_DRAGON_IRON_BLOCK.get());

        tag(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.FANCY_DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_PANE.get())
                .add(ModBlocks.DRAGON_IRON_BUTTON.get())
                .add(ModBlocks.DRAGON_IRON_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_FENCE.get())
                .add(ModBlocks.DRAGON_IRON_FENCE_GATE.get())
                .add(ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get())
                .add(ModBlocks.DRAGON_IRON_SLAB.get())
                .add(ModBlocks.DRAGON_IRON_STAIRS.get())
                .add(ModBlocks.DRAGON_IRON_TRAP_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_WALL.get())
                .add(ModBlocks.DRAGON_IRON_FURNACE.get());

        tag(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.FANCY_DRAGON_IRON_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_BLOCK.get())
                .add(ModBlocks.DRAGON_GLASS_PANE.get())
                .add(ModBlocks.DRAGON_IRON_BUTTON.get())
                .add(ModBlocks.DRAGON_IRON_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_FENCE.get())
                .add(ModBlocks.DRAGON_IRON_FENCE_GATE.get())
                .add(ModBlocks.DRAGON_IRON_PRESSURE_PLATE.get())
                .add(ModBlocks.DRAGON_IRON_SLAB.get())
                .add(ModBlocks.DRAGON_IRON_STAIRS.get())
                .add(ModBlocks.DRAGON_IRON_TRAP_DOOR.get())
                .add(ModBlocks.DRAGON_IRON_WALL.get())
                .add(ModBlocks.DRAGON_IRON_FURNACE.get());

        tag(BlockTags.FENCES)
                .add(ModBlocks.DRAGON_IRON_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.DRAGON_IRON_FENCE_GATE.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.DRAGON_IRON_WALL.get());

        tag(ModTags.Blocks.NEEDS_DRAGON_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_DRAGON_IRON_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_DRAGON_IRON_TOOL);

        tag(ModTags.Blocks.MINEABLE_WITH_HAMMER)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
