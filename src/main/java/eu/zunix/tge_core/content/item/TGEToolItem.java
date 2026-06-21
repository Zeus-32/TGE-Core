package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.TGEToolMaterial;
import eu.zunix.tge_core.content.tool.TGEToolType;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class TGEToolItem extends Item {
    private final TGEToolType type;
    private final TGEToolMaterial material;

    public TGEToolItem(TGEToolType type, TGEToolMaterial material, Properties properties) {
        super(properties);
        this.type = type;
        this.material = material;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        TGEToolTooltip.append(stack, material, tooltipComponents);
    }

    public TGEToolType toolType() {
        return type;
    }

    public TGEToolMaterial toolMaterial() {
        return material;
    }
}
