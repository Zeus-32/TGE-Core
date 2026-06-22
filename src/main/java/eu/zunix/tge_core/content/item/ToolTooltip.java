package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.ToolMaterial;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class ToolTooltip {
    private ToolTooltip() {
    }

    public static void append(ItemStack stack, ToolMaterial material, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.literal(String.valueOf(stack.getMaxDamage())).withStyle(ChatFormatting.WHITE)
                .append(Component.literal(" "))
                .append(Component.translatable("tooltip.tge.tool.max_durability").withStyle(ChatFormatting.YELLOW))
        );
        tooltipComponents.add(Component.literal(String.valueOf(stack.getMaxDamage() - stack.getDamageValue())).withStyle(ChatFormatting.WHITE)
                .append(Component.literal(" "))
                .append(Component.translatable("tooltip.tge.tool.durability").withStyle(ChatFormatting.GREEN))
        );
        tooltipComponents.add(Component.literal(String.valueOf(craftingUses(stack))).withStyle(ChatFormatting.WHITE)
                .append(Component.literal(" "))
                .append(Component.translatable("tooltip.tge.tool.crafting_uses").withStyle(ChatFormatting.AQUA))
        );
        tooltipComponents.add(Component.literal(String.valueOf(material.displayTier())).withStyle(material.tierColor())
                .append(Component.literal(" "))
                .append(Component.translatable("tooltip.tge.tool.tier").withStyle(ChatFormatting.WHITE))
                .append(
                        Component.literal(" (").withStyle(ChatFormatting.WHITE)
                        .append(Component.literal(material.displayTierNumber()).withStyle(material.tierColor()))
                        .append(Component.literal(")").withStyle(ChatFormatting.WHITE))
                )
        );
    }

    public static void appendAbility(String nameKey, String descriptionKey, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.empty());
        tooltipComponents.add(Component.translatable(nameKey).withStyle(ChatFormatting.GOLD)
                .append(Component.literal(": ").withStyle(ChatFormatting.GRAY))
                .append(Component.translatable(descriptionKey).withStyle(ChatFormatting.WHITE)));
    }

    private static int craftingUses(ItemStack stack) {
        return (stack.getMaxDamage() - stack.getDamageValue()) / ToolCrafting.DAMAGE_PER_CRAFT;
    }
}
