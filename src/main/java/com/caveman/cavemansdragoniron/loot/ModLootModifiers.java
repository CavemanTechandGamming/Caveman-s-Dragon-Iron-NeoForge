package com.caveman.cavemansdragoniron.loot;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CavemansDragonIron.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM_WITH_CONFIG_CHANCE =
            LOOT_MODIFIER_SERIALIZERS.register("add_item_with_config_chance", () -> AddItemWithConfigChanceModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_LORE_BOOK =
            LOOT_MODIFIER_SERIALIZERS.register("add_lore_book", () -> AddLoreBookModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM_WITH_CHANCE =
            LOOT_MODIFIER_SERIALIZERS.register("add_item_with_chance", () -> AddItemWithChanceModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM_WITH_CHANCE_AND_COUNT =
            LOOT_MODIFIER_SERIALIZERS.register("add_item_with_chance_and_count", () -> AddItemWithChanceAndCountModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ENCHANTED_DRAGON_IRON_GEAR =
            LOOT_MODIFIER_SERIALIZERS.register("add_enchanted_dragon_iron_gear", () -> AddEnchantedDragonIronGearModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_CHUNK_EATER_BOOK =
            LOOT_MODIFIER_SERIALIZERS.register("add_chunk_eater_book", () -> AddChunkEaterBookModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
