package net.zeus_32.tge_core.screen.custom;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.block.entity.CokeOvenBlockEntity;
import net.zeus_32.tge_core.screen.ModMenuTypes;

public class CokeOvenMenu extends AbstractContainerMenu {
    public final CokeOvenBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public CokeOvenMenu(int pContainerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(pContainerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public CokeOvenMenu(int pContainerId, Inventory inventory, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.COKE_OVEN_MENU.get(), pContainerId);
        this.blockEntity = ((CokeOvenBlockEntity) entity);
        this.level = inventory.player.level();
        this.data = data;

        // Přidání slotů pro Coke Oven
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 0, 54, 34) { // Vstupní slot
            @Override
            public boolean mayPlace(ItemStack stack) {
                return true; // Povoluje vkládání předmětů
            }
        });

        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 1, 104, 34) { // Výstupní slot
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; // Zakazuje ruční vkládání
            }
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            if (index < 2) { // Sloty v Coke Oven
                if (!this.moveItemStackTo(stackInSlot, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else { // Hráčův inventář
                if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) { // Pouze první slot přijímá vstupy
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.COKE_OVEN.get());
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledArrowProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 24;

        return maxProgress != 0 ? progress * progressBarSize / maxProgress : 0;
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }
}