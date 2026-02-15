package com.caveman.cavemansdragoniron.villager;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, CavemansDragonIron.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, CavemansDragonIron.MOD_ID);


    public static final Holder<PoiType> DRAGON_SMITH_POI = POI_TYPES.register("dragon_smith_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.DRAGON_IRON_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final Holder<VillagerProfession> DRAGON_SMITH = VILLAGER_PROFESSIONS.register("dragon_smith",
            () -> new VillagerProfession("dragon_smith", holder -> holder.value() == DRAGON_SMITH_POI.value(),
                    poiTypeHolder -> poiTypeHolder.value() == DRAGON_SMITH_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.ANVIL_LAND));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
