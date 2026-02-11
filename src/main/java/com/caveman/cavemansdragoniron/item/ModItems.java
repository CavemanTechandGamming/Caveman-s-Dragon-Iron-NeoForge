package com.caveman.cavemansdragoniron.item;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.item.custom.HammerItem;
import com.caveman.cavemansdragoniron.item.custom.ModArmorItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CavemansDragonIron.MOD_ID);

    public static final DeferredItem<Item> DRAGON_IRON_NUGGET = ITEMS.register("dragon_iron_nugget",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<Item> DRAGON_IRON_INGOT = ITEMS.register("dragon_iron_ingot",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    // ===== Weapons & tools =====
    public static final DeferredItem<SwordItem> DRAGON_IRON_SWORD = ITEMS.register("dragon_iron_sword",
            () -> new SwordItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DRAGON_IRON, 3, -2.4F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<ShovelItem> DRAGON_IRON_SHOVEL = ITEMS.register("dragon_iron_shovel",
            () -> new ShovelItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.DRAGON_IRON, 1.5F, -3.0F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<PickaxeItem> DRAGON_IRON_PICKAXE = ITEMS.register("dragon_iron_pickaxe",
            () -> new PickaxeItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.DRAGON_IRON, 1.0F, -2.8F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<AxeItem> DRAGON_IRON_AXE = ITEMS.register("dragon_iron_axe",
            () -> new AxeItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.DRAGON_IRON, 6.0F, -3.2F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<HoeItem> DRAGON_IRON_HOE = ITEMS.register("dragon_iron_hoe",
            () -> new HoeItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.DRAGON_IRON, 0.0F, -3.0F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static final DeferredItem<HammerItem> DRAGON_IRON_HAMMER = ITEMS.register("dragon_iron_hammer",
            () -> new HammerItem(ModToolTiers.DRAGON_IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.DRAGON_IRON, 7.0F, -3.5F))
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    // ===== Armor =====
    public static final DeferredItem<ArmorItem> DRAGON_IRON_HELMET = ITEMS.register("dragon_iron_helmet",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_IRON_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(100))
                            .rarity(Rarity.EPIC)
                            .fireResistant()));

    public static final DeferredItem<ArmorItem> DRAGON_IRON_CHESTPLATE = ITEMS.register("dragon_iron_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_IRON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(100))
                            .rarity(Rarity.EPIC)
                            .fireResistant()));

    public static final DeferredItem<ArmorItem> DRAGON_IRON_LEGGINGS = ITEMS.register("dragon_iron_leggings",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_IRON_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(100))
                            .rarity(Rarity.EPIC)
                            .fireResistant()));

    public static final DeferredItem<ArmorItem> DRAGON_IRON_BOOTS = ITEMS.register("dragon_iron_boots",
            () -> new ModArmorItem(ModArmorMaterials.DRAGON_IRON_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(100))
                            .rarity(Rarity.EPIC)
                            .fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
