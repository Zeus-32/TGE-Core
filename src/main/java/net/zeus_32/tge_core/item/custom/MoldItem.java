package net.zeus_32.tge_core.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MoldItem extends Item{
    public MoldItem(Properties properties) {
        super(properties);
    }

    @Override
        public boolean hasCraftingRemainingItem(ItemStack stack) {
            return true;
        }

        @Override
        public ItemStack getCraftingRemainingItem(ItemStack stack) {
            ItemStack copy = stack.copy();
            copy.setDamageValue(copy.getDamageValue());
            return copy;
        }
}
