package com.caveman.cavemansdragoniron.item;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CavemansDragonIron.MOD_ID);

    public static final DeferredItem<Item> DRAGON_IRON_NUGGET = ITEMS.register("dragon_iron_nugget",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> DRAGON_IRON_INGOT = ITEMS.register("dragon_iron_ingot",
            () -> new Item(new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
