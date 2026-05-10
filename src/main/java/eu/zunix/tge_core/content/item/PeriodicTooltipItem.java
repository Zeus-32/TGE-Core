package eu.zunix.tge_core.content.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class PeriodicTooltipItem extends Item {
    private final String formula;

    public PeriodicTooltipItem(String formula, Properties properties) {
        super(properties);
        this.formula = formula;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.tge_core.formula", formula).withStyle(ChatFormatting.YELLOW));
    }
}
