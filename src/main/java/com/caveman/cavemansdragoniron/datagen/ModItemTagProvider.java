package com.caveman.cavemansdragoniron.datagen;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.item.ModItems;
import com.caveman.cavemansdragoniron.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, CavemansDragonIron.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // ===== Beacon payments =====
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.DRAGON_IRON_INGOT.get())
                .add(ModItems.DRAGON_IRON_NUGGET.get());

        // ===== Tool & weapon tags =====
        tag(ItemTags.AXES)
                .add(ModItems.DRAGON_IRON_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.DRAGON_IRON_HOE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.DRAGON_IRON_PICKAXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.DRAGON_IRON_SHOVEL.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.DRAGON_IRON_SWORD.get());

        // ===== Chunk Eater enchantment (pickaxe, shovel, axe, hammer) =====
        tag(ModTags.Items.CHUNK_EATER_TOOLS)
                .addTags(ItemTags.PICKAXES, ItemTags.SHOVELS, ItemTags.AXES)
                .add(ModItems.DRAGON_IRON_HAMMER.get());

        // ===== Armor with armor trim =====
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DRAGON_IRON_HELMET.get())
                .add(ModItems.DRAGON_IRON_CHESTPLATE.get())
                .add(ModItems.DRAGON_IRON_LEGGINGS.get())
                .add(ModItems.DRAGON_IRON_BOOTS.get());
    }
}
