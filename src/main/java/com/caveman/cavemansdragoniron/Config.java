package com.caveman.cavemansdragoniron;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue DRAGON_IRON_NUGGET_ENDERMAN_DROP_CHANCE_PERCENT = BUILDER
            .comment("Base chance (0-100%) for Endermen to drop a dragon iron nugget when killed without Looting. Looting increases this chance by 10% per level (hardcoded). Default 10% is similar to zombified piglin gold nugget.")
            .translation("config.cavemansdragoniron.common.dragonIronNuggetEndermanDropChancePercent")
            .defineInRange("dragonIronNuggetEndermanDropChancePercent", 10, 0, 100);

    static final ModConfigSpec SPEC = BUILDER.build();
}
