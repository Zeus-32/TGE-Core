package net.zeus_32.tge_core.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnifeItem extends Item {
    public KnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamageValue(copy.getDamageValue() + 2);
        return copy;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        int maxDurability = stack.getMaxDamage();
        int currentDamage = stack.getDamageValue();
        int remainingDurability = maxDurability - currentDamage;
        int usesLeft = remainingDurability / 2;

        tooltip.add(Component.literal("§7Durability: §b" + remainingDurability));
        tooltip.add(Component.literal("§7Crafting uses: §e" + usesLeft));

        super.appendHoverText(stack, context, tooltip, flag);
    }
}