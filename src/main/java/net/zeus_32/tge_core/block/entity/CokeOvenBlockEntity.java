package net.zeus_32.tge_core.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.zeus_32.tge_core.block.entity.renderer.ModBlockEntities;
import net.zeus_32.tge_core.recipe.CokeOvenRecipe;
import net.zeus_32.tge_core.recipe.CokeOvenRecipeInput;
import net.zeus_32.tge_core.recipe.ModRecipes;
import net.zeus_32.tge_core.screen.custom.CokeOvenMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CokeOvenBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 600;

    public CokeOvenBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COKE_OVEN_BE.get(), pos, blockState);
        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> CokeOvenBlockEntity.this.progress;
                    case 1 -> CokeOvenBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: CokeOvenBlockEntity.this.progress = value;
                    case 1: CokeOvenBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.tge_core.coke_oven");
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CokeOvenMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("coke_oven.progress", progress);
        pTag.putInt("coke_oven.max_progress", maxProgress);
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("coke_oven.progress");
        maxProgress = pTag.getInt("coke_oven.max_progress");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockstate) {
        if (hasRecipe()) {
            doProgress();
            setChanged(level, blockPos, blockstate);

            if (finishedCrafting()) {
                craft();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craft() {
        Optional<RecipeHolder<CokeOvenRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().getOutput();

        itemHandler.extractItem(INPUT_SLOT, 1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(), itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 600;
    }

    private boolean finishedCrafting() {
        return this.progress >= this.maxProgress;
    }

    private void doProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<CokeOvenRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().getOutput();
        return canInsertAmountToOutput(output.getCount()) && canInsertIntoOutput(output);
    }

    private Optional<RecipeHolder<CokeOvenRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.COKE_OVEN_TYPE.get(), new CokeOvenRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertIntoOutput(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountToOutput(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return super.getUpdateTag(registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return super.getUpdatePacket();
    }
}