package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.TGEToolMaterial;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class TGEToolTooltip {
    private TGEToolTooltip() {
    }

    public static void append(ItemStack stack, TGEToolMaterial material, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("tooltip.tge.tool.max_durability").withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(String.valueOf(stack.getMaxDamage())).withStyle(ChatFormatting.WHITE)));
        tooltipComponents.add(Component.translatable("tooltip.tge.tool.durability").withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(String.valueOf(stack.getMaxDamage() - stack.getDamageValue())).withStyle(ChatFormatting.WHITE)));
        tooltipComponents.add(Component.translatable("tooltip.tge.tool.crafting_uses").withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(String.valueOf(material.craftingUses())).withStyle(ChatFormatting.WHITE)));
        tooltipComponents.add(Component.translatable("tooltip.tge.tool.tier").withStyle(ChatFormatting.YELLOW)
                .append(Component.literal(String.valueOf(material.displayTier())).withStyle(ChatFormatting.WHITE)));
    }
}
