package com.caveman.cavemansdragoniron.menu;

import com.caveman.cavemansdragoniron.block.entity.DragonIronFurnaceBlockEntity;
import com.caveman.cavemansdragoniron.menu.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.Container;
import net.minecraft.core.BlockPos;

public class DragonIronFurnaceMenu extends AbstractContainerMenu {

    /** Standard slot size; both input and result use this (vanilla draws all slots 16x16). */
    public static final int SLOT_SIZE = 16;
    /** Input slot: mirrored across the arrow from result, same Y and size. */
    public static final int INPUT_SLOT_X = 50;   // mirrored from result (116) across arrow center (~91)
    public static final int INPUT_SLOT_Y = 35;
    /** Result slot position. */
    public static final int RESULT_SLOT_X = 116;
    public static final int RESULT_SLOT_Y = 35;

    private final Container container;
    private final ContainerData data;

    public DragonIronFurnaceMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
        super(ModMenuTypes.DRAGON_IRON_FURNACE.get(), containerId);
        this.container = container;
        this.data = data;
        checkContainerSize(container, 2);
        checkContainerDataCount(data, 2);

        addSlot(new Slot(container, DragonIronFurnaceBlockEntity.SLOT_INPUT, INPUT_SLOT_X, INPUT_SLOT_Y));
        addSlot(new FurnaceResultSlot(playerInventory.player, container, DragonIronFurnaceBlockEntity.SLOT_RESULT, RESULT_SLOT_X, RESULT_SLOT_Y));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        addDataSlots(data);
    }

    public static DragonIronFurnaceMenu fromNetwork(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(pos);
        if (blockEntity instanceof DragonIronFurnaceBlockEntity furnace) {
            return new DragonIronFurnaceMenu(containerId, playerInventory, furnace, furnace.getData());
        }
        throw new IllegalStateException("Block entity at " + pos + " is not DragonIronFurnaceBlockEntity");
    }

    public int getCookingProgress() {
        return data.get(0);
    }

    public int getCookingTotalTime() {
        int total = data.get(1);
        return total == 0 ? 100 : total;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            stack = stackInSlot.copy();
            if (index == DragonIronFurnaceBlockEntity.SLOT_RESULT) {
                if (!moveItemStackTo(stackInSlot, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, stack);
            } else if (index >= 2) {
                if (DragonIronFurnaceBlockEntity.SLOT_INPUT >= 0 && container.canPlaceItem(DragonIronFurnaceBlockEntity.SLOT_INPUT, stackInSlot)) {
                    if (!moveItemStackTo(stackInSlot, DragonIronFurnaceBlockEntity.SLOT_INPUT, DragonIronFurnaceBlockEntity.SLOT_INPUT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stackInSlot, 2, 38, false)) {
                return ItemStack.EMPTY;
            }
            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stackInSlot);
        }
        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    public ContainerData getData() {
        return data;
    }
}
