package com.caveman.cavemansdragoniron.enchantment;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.enchantment.custom.ChunkEaterEnchantmentEffect;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * Registers custom enchantment effect component types for use in enchantment JSON.
 * The chunk_eater effect is referenced in data/cavemansdragoniron/enchantment/chunk_eater.json.
 */
public class ModEnchantmentEffects {
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_EFFECT_COMPONENT_TYPES =
            DeferredRegister.create(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, CavemansDragonIron.MOD_ID);

    public static final Supplier<DataComponentType<ChunkEaterEnchantmentEffect>> CHUNK_EATER =
            ENCHANTMENT_EFFECT_COMPONENT_TYPES.register("chunk_eater",
                    () -> DataComponentType.<ChunkEaterEnchantmentEffect>builder()
                            .persistent(ChunkEaterEnchantmentEffect.CODEC)
                            .build());

    public static void register(net.neoforged.bus.api.IEventBus eventBus) {
        ENCHANTMENT_EFFECT_COMPONENT_TYPES.register(eventBus);
    }
}
