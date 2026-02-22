package com.caveman.cavemansdragoniron.block;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CavemansDragonIron.MOD_ID);

    public static final DeferredBlock<Block> DRAGON_IRON_BLOCK = registerBlock("dragon_iron_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f, 2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
            ));

    public static final DeferredBlock<Block> FANCY_DRAGON_IRON_BLOCK = registerBlock("fancy_dragon_iron_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f, 2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
            ));

    public static final DeferredBlock<Block> DRAGON_GLASS_BLOCK = registerBlock("dragon_glass_block",
            () -> new TintedGlassBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.GLASS)
                    .strength(20f, 2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
                    .noOcclusion()
                    .isValidSpawn((state, level, pos, entity) -> false)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            ));

    public static final DeferredBlock<IronBarsBlock> DRAGON_GLASS_PANE = registerBlock("dragon_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.GLASS)
                    .strength(20f, 2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
                    .noOcclusion()
                    .isValidSpawn((state, level, pos, entity) -> false)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            ));

    public static final DeferredBlock<StairBlock> DRAGON_IRON_STAIRS = registerBlock("dragon_iron_stairs",
            () -> new StairBlock(ModBlocks.DRAGON_IRON_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
            ));
    public static final DeferredBlock<SlabBlock> DRAGON_IRON_SLAB = registerBlock("dragon_iron_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
            ));


    public static final DeferredBlock<PressurePlateBlock> DRAGON_IRON_PRESSURE_PLATE = registerBlock("dragon_iron_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<ButtonBlock> DRAGON_IRON_BUTTON = registerBlock("dragon_iron_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
                    .noCollission()
            ));


    public static final DeferredBlock<FenceBlock> DRAGON_IRON_FENCE = registerBlock("dragon_iron_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<FenceGateBlock> DRAGON_IRON_FENCE_GATE = registerBlock("dragon_iron_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
            ));
    public static final DeferredBlock<WallBlock> DRAGON_IRON_WALL = registerBlock("dragon_iron_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
            ));


    public static final DeferredBlock<DoorBlock> DRAGON_IRON_DOOR = registerBlock("dragon_iron_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
            ));
    public static final DeferredBlock<TrapDoorBlock> DRAGON_IRON_TRAP_DOOR = registerBlock("dragon_iron_trap_door",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f,2400f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
            ));

    public static final DeferredBlock<DragonIronFurnaceBlock> DRAGON_IRON_FURNACE = registerBlock("dragon_iron_furnace",
            () -> new DragonIronFurnaceBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .strength(20f, 2400f)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.IGNORE)
                    .lightLevel(state -> state.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.LIT) ? 13 : 0)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void  registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(Rarity.EPIC).fireResistant()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
