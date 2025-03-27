package net.zeus_32.tge_core.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class CokeOvenRecipeInput implements RecipeInput {
    private final ItemStack input;

    public CokeOvenRecipeInput(ItemStack stackInSlot) {
        this.input = stackInSlot;
    }

    @Override
    public ItemStack getItem(int i) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}