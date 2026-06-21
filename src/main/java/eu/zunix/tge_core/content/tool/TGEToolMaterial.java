package eu.zunix.tge_core.content.tool;

import net.minecraft.world.item.Tier;

public record TGEToolMaterial(String id, String displayName, String displayTier, int durability, Tier tier) {
    public int craftingUses() {
        return durability / 4;
    }
}
