package eu.zunix.tge_core.content.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class PeriodicTooltipBlockItem extends BlockItem {
    private final String formula;

    public PeriodicTooltipBlockItem(Block block, String formula, Properties properties) {
        super(block, properties);
        this.formula = formula;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (!formula.isBlank()) {
            tooltipComponents.add(Component.translatable("tooltip.tge.formula", formula).withStyle(ChatFormatting.YELLOW));
        }
    }
}
