package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.ToolMaterial;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;

public class PickaxeToolItem extends PickaxeItem {
    private final ToolMaterial material;

    public PickaxeToolItem(ToolMaterial material, Properties properties) {
        super(material.tier(), properties);
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
}
