package com.caveman.cavemansdragoniron.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, "cavemansdragoniron", "en_us");
    }

    @Override
    protected void addTranslations() {

        // =====================
        // ITEMS
        // =====================

        add("item.cavemansdragoniron.dragon_iron_boots", "Dragon Iron Boots");
        add("item.cavemansdragoniron.dragon_iron_chestplate", "Dragon Iron Chestplate");
        add("item.cavemansdragoniron.dragon_iron_helmet", "Dragon Iron Helmet");
        add("item.cavemansdragoniron.dragon_iron_leggings", "Dragon Iron Leggings");
        add("item.cavemansdragoniron.dragon_iron_axe", "Dragon Iron Axe");
        add("item.cavemansdragoniron.dragon_iron_hammer", "Dragon Iron Hammer");
        add("item.cavemansdragoniron.dragon_iron_hoe", "Dragon Iron Hoe");
        add("item.cavemansdragoniron.dragon_iron_ingot", "Dragon Iron Ingot");
        add("item.cavemansdragoniron.dragon_iron_nugget", "Dragon Iron Nugget");
        add("item.cavemansdragoniron.dragon_iron_pickaxe", "Dragon Iron Pickaxe");
        add("item.cavemansdragoniron.dragon_iron_shovel", "Dragon Iron Shovel");
        add("item.cavemansdragoniron.dragon_iron_sword", "Dragon Iron Sword");

        // =====================
        // BLOCKS
        // =====================

        add("block.cavemansdragoniron.dragon_glass_block", "Dragon Glass Block");
        add("block.cavemansdragoniron.dragon_glass_pane", "Dragon Glass Pane");
        add("block.cavemansdragoniron.dragon_iron_block", "Dragon Iron Block");
        add("block.cavemansdragoniron.dragon_iron_button", "Dragon Iron Button");
        add("block.cavemansdragoniron.dragon_iron_door", "Dragon Iron Door");
        add("block.cavemansdragoniron.dragon_iron_fence", "Dragon Iron Fence");
        add("block.cavemansdragoniron.dragon_iron_fence_gate", "Dragon Iron Fence Gate");
        add("block.cavemansdragoniron.dragon_iron_pressure_plate", "Dragon Iron Pressure Plate");
        add("block.cavemansdragoniron.dragon_iron_slab", "Dragon Iron Slab");
        add("block.cavemansdragoniron.dragon_iron_stairs", "Dragon Iron Stairs");
        add("block.cavemansdragoniron.dragon_iron_trap_door", "Dragon Iron Trapdoor");
        add("block.cavemansdragoniron.dragon_iron_wall", "Dragon Iron Wall");
        add("block.cavemansdragoniron.fancy_dragon_iron_block", "Fancy Dragon Iron Block");

        // =====================
        // ENTITY
        // =====================

        add("entity.minecraft.villager.cavemansdragoniron.dragon_smith", "Dragon Smith");

        // =====================
        // CREATIVE TAB
        // =====================

        add("creativetab.cavemansdragoniron.cavemans_dragon_iron_items", "Caveman's Dragon Iron");
    }
}
