package eu.zunix.tge_core.content.tool;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public final class ToolTier implements Tier {
    private final Tier base;
    private final int durability;

    public ToolTier(Tier base, int durability) {
        this.base = base;
        this.durability = durability;
    }

    @Override
    public int getUses() {
        return durability;
    }

    @Override
    public float getSpeed() {
        return base.getSpeed();
    }

    @Override
    public float getAttackDamageBonus() {
        return base.getAttackDamageBonus();
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return base.getIncorrectBlocksForDrops();
    }

    @Override
    public int getEnchantmentValue() {
        return base.getEnchantmentValue();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return base.getRepairIngredient();
    }
}
