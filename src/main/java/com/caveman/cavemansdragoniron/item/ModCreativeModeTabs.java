package com.caveman.cavemansdragoniron.item;

import com.caveman.cavemansdragoniron.book.ModBooks;
import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.core.component.DataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CavemansDragonIron.MOD_ID);

    public static final Supplier<CreativeModeTab> CAVEMANS_DRAGON_IRON_ITEMS_TAB = CREATIVE_MODE_TAB.register("cavemans_dragon_iron_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.DRAGON_IRON_BLOCK))
                    .title(Component.translatable("creativetab.cavemansdragoniron.cavemans_dragon_iron_items"))
                    .displayItems(((itemDisplayParameters, output) -> {

                        // ===== Ingredients =====
                        output.accept(ModItems.DRAGON_IRON_NUGGET);
                        output.accept(ModItems.DRAGON_IRON_INGOT);
                        output.accept(ModBlocks.DRAGON_IRON_BLOCK);
                        output.accept(ModBlocks.FANCY_DRAGON_IRON_BLOCK);


                        // ===== Weapons & tools =====
                        output.accept(ModItems.DRAGON_IRON_SWORD);
                        output.accept(ModItems.DRAGON_IRON_PICKAXE);
                        output.accept(ModItems.DRAGON_IRON_HAMMER);
                        output.accept(ModItems.DRAGON_IRON_AXE);
                        output.accept(ModItems.DRAGON_IRON_SHOVEL);
                        output.accept(ModItems.DRAGON_IRON_HOE);


                        // ===== Armor =====
                        output.accept(ModItems.DRAGON_IRON_HELMET);
                        output.accept(ModItems.DRAGON_IRON_CHESTPLATE);
                        output.accept(ModItems.DRAGON_IRON_LEGGINGS);
                        output.accept(ModItems.DRAGON_IRON_BOOTS);


                        // ===== Enchantment book (for testing) =====
                        ResourceKey<Enchantment> chunkEaterKey = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(CavemansDragonIron.MOD_ID, "chunk_eater"));
                        itemDisplayParameters.holders().lookupOrThrow(Registries.ENCHANTMENT)
                                .get(chunkEaterKey)
                                .ifPresent(holder -> {
                                    ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                                    mutable.set(holder, 1);
                                    ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
                                    book.set(DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable());
                                    output.accept(book);
                                });

                        // ===== Lore books =====
                        output.accept(ModBooks.getStack("volume_1_the_fragment"));
                        output.accept(ModBooks.getStack("volume_2_the_smith_who_failed"));
                        output.accept(ModBooks.getStack("volume_3_the_eyes_that_open"));
                        output.accept(ModBooks.getStack("volume_4_the_dragon_and_the_flame"));
                        output.accept(ModBooks.getStack("volume_5_the_first_dragon_smith"));
                        output.accept(ModBooks.getStack("epilogue_the_flame_that_remains"));

                        // ===== Blocks =====
                        output.accept(ModBlocks.DRAGON_GLASS_BLOCK);
                        output.accept(ModBlocks.DRAGON_GLASS_PANE);
                        output.accept(ModBlocks.DRAGON_IRON_BUTTON);
                        output.accept(ModBlocks.DRAGON_IRON_DOOR);
                        output.accept(ModBlocks.DRAGON_IRON_FENCE);
                        output.accept(ModBlocks.DRAGON_IRON_FENCE_GATE);
                        output.accept(ModBlocks.DRAGON_IRON_PRESSURE_PLATE);
                        output.accept(ModBlocks.DRAGON_IRON_SLAB);
                        output.accept(ModBlocks.DRAGON_IRON_STAIRS);
                        output.accept(ModBlocks.DRAGON_IRON_TRAP_DOOR);
                        output.accept(ModBlocks.DRAGON_IRON_WALL);
                        output.accept(ModBlocks.DRAGON_IRON_FURNACE);


                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
