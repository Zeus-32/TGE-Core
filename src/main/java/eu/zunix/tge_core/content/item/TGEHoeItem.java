package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.TGEToolMaterial;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class TGEHoeItem extends HoeItem {
    private final TGEToolMaterial material;

    public TGEHoeItem(TGEToolMaterial material, Properties properties) {
        super(material.tier(), properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        TGEToolTooltip.append(stack, material, tooltipComponents);
    }
}
