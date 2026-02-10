package com.caveman.cavemansdragoniron.item;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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


                        // ===== Items =====


                        // ===== Blocks =====
                        output.accept(ModBlocks.DRAGON_IRON_BUTTON);
                        output.accept(ModBlocks.DRAGON_IRON_DOOR);
                        output.accept(ModBlocks.DRAGON_IRON_FENCE);
                        output.accept(ModBlocks.DRAGON_IRON_FENCE_GATE);
                        output.accept(ModBlocks.DRAGON_IRON_PRESSURE_PLATE);
                        output.accept(ModBlocks.DRAGON_IRON_SLAB);
                        output.accept(ModBlocks.DRAGON_IRON_STAIRS);
                        output.accept(ModBlocks.DRAGON_IRON_TRAP_DOOR);
                        output.accept(ModBlocks.DRAGON_IRON_WALL);


                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
