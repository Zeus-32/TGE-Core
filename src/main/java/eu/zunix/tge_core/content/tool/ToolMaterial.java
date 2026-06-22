package eu.zunix.tge_core.content.tool;

import eu.zunix.tge_core.content.item.ToolCrafting;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Tier;

public record ToolMaterial(
        String id,
        String displayName,
        String displayTier,
        String displayTierNumber,
        ChatFormatting tierColor,
        int durability,
        Tier tier) {
    public int craftingUses() {
        return durability / ToolCrafting.DAMAGE_PER_CRAFT;
    }
}
