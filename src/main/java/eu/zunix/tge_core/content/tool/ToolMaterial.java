package eu.zunix.tge_core.content.tool;

import eu.zunix.tge_core.content.item.ToolCrafting;
import net.minecraft.world.item.Tier;

public record ToolMaterial(String id, String displayName, String displayTier, int durability, Tier tier) {
    public int craftingUses() {
        return durability / ToolCrafting.DAMAGE_PER_CRAFT;
    }
}
