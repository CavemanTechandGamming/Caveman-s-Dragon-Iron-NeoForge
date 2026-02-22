package com.caveman.cavemansdragoniron.enchantment.custom;

import com.mojang.serialization.Codec;

/**
 * Marker effect component for the Chunk Eater enchantment.
 * Logic is implemented in ModEvents (block break handler).
 */
public record ChunkEaterEnchantmentEffect() {
    /** Empty object in JSON; single instance. */
    public static final Codec<ChunkEaterEnchantmentEffect> CODEC =
            Codec.unit(new ChunkEaterEnchantmentEffect());
}
