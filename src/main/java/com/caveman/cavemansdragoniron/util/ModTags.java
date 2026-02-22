package com.caveman.cavemansdragoniron.util;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_DRAGON_IRON_TOOL = createTag("needs_dragon_iron_tool");
        public static final TagKey<Block> INCORRECT_FOR_DRAGON_IRON_TOOL = createTag("incorrect_for_dragon_iron_tool");
        /** Blocks the hammer can mine (pickaxe + shovel). */
        public static final TagKey<Block> MINEABLE_WITH_HAMMER = createTag("mineable_with_hammer");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, name));
        }
    }

    public static class Items {
        /** Items that can receive the Chunk Eater enchantment (pickaxes, shovels, axes, hammer). */
        public static final TagKey<Item> CHUNK_EATER_TOOLS = createTag("chunk_eater_tools");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, name));
        }
    }
}