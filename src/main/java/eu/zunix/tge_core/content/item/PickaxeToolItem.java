package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.ToolMaterial;
import java.util.List;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;

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
        ToolTooltip.appendAbility(
                "tooltip.tge.ability.torchbearer",
                "tooltip.tge.ability.torchbearer.description",
                tooltipComponents);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) {
            return super.useOn(context);
        }

        ItemStack torch = findTorch(player);
        if (torch.isEmpty()) {
            return super.useOn(context);
        }

        UseOnContext torchContext = new UseOnContext(
                context.getLevel(),
                player,
                context.getHand(),
                torch,
                new BlockHitResult(
                        context.getClickLocation(),
                        context.getClickedFace(),
                        context.getClickedPos(),
                        context.isInside()));
        InteractionResult result = torch.getItem().useOn(torchContext);
        return result.consumesAction() ? result : super.useOn(context);
    }

    private static ItemStack findTorch(Player player) {
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(net.minecraft.world.item.Items.TORCH)) {
            return offhand;
        }

        Inventory inventory = player.getInventory();
        for (ItemStack stack : inventory.items) {
            if (stack.is(net.minecraft.world.item.Items.TORCH)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
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
