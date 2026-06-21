package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.TGEToolMaterial;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;

public class TGESwordItem extends SwordItem {
    private final TGEToolMaterial material;

    public TGESwordItem(TGEToolMaterial material, Properties properties) {
        super(material.tier(), properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        TGEToolTooltip.append(stack, material, tooltipComponents);
    }
}
