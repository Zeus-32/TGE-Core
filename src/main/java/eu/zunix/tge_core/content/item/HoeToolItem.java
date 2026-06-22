package eu.zunix.tge_core.content.item;

import eu.zunix.tge_core.content.tool.ToolMaterial;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

public class HoeToolItem extends HoeItem {
    private final ToolMaterial material;

    public HoeToolItem(ToolMaterial material, Properties properties) {
        super(material.tier(), properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(
            ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ToolTooltip.append(stack, material, tooltipComponents);
        ToolTooltip.appendAbility(
                "tooltip.tge.ability.farmer",
                "tooltip.tge.ability.farmer.description",
                tooltipComponents);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = super.useOn(context);
        Player player = context.getPlayer();
        if (!result.consumesAction() || player == null || context.getLevel().isClientSide()) {
            return result;
        }

        tillArea(context, player);
        return result;
    }

    private static void tillArea(UseOnContext context, Player player) {
        Level level = context.getLevel();
        BlockPos origin = context.getClickedPos();
        for (BlockPos pos : BlockPos.betweenClosed(origin.offset(-1, 0, -1), origin.offset(1, 0, 1))) {
            BlockPos target = pos.immutable();
            if (target.equals(origin)) {
                continue;
            }

            UseOnContext targetContext = new UseOnContext(
                    level,
                    player,
                    context.getHand(),
                    context.getItemInHand(),
                    new BlockHitResult(context.getClickLocation(), context.getClickedFace(), target, context.isInside()));
            if (context.getClickedFace() == net.minecraft.core.Direction.DOWN
                    || !level.getBlockState(target.above()).isAir()) {
                continue;
            }

            BlockState tilled = level.getBlockState(target).getToolModifiedState(targetContext, ItemAbilities.HOE_TILL, false);
            if (tilled == null) {
                continue;
            }

            level.playSound(player, target, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlock(target, tilled, 11);
            context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
        }
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
