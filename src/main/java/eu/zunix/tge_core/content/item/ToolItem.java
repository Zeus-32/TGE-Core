package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.ToolMaterial;
import eu.zunix.tge_core.content.tool.ToolType;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class ToolItem extends Item {
    private final ToolType type;
    private final ToolMaterial material;

    public ToolItem(ToolType type, ToolMaterial material, Properties properties) {
        super(properties);
        this.type = type;
        this.material = material;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ToolTooltip.append(stack, material, tooltipComponents);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return ToolCrafting.hasCraftingRemainingItem(stack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        return ToolCrafting.getCraftingRemainingItem(stack);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return ToolCrafting.isBarVisible(stack);
    }

    public ToolType toolType() {
        return type;
    }

    public ToolMaterial toolMaterial() {
        return material;
    }
}
