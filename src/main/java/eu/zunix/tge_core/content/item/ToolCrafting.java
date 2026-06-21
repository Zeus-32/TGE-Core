package eu.zunix.tge_core.content.item;

import net.minecraft.world.item.ItemStack;

public final class ToolCrafting {
    public static final int DAMAGE_PER_CRAFT = 2;

    private ToolCrafting() {
    }

    public static boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.isDamageableItem();
    }

    public static ItemStack getCraftingRemainingItem(ItemStack stack) {
        if (!stack.isDamageableItem()) {
            return ItemStack.EMPTY;
        }

        int nextDamage = stack.getDamageValue() + DAMAGE_PER_CRAFT;
        if (nextDamage >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }

        ItemStack remainder = stack.copy();
        remainder.setCount(1);
        remainder.setDamageValue(nextDamage);
        return remainder;
    }

    public static boolean isBarVisible(ItemStack stack) {
        return stack.isDamageableItem();
    }
}
