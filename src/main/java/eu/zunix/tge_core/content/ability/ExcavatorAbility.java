package eu.zunix.tge_core.content.ability;

import eu.zunix.tge_core.content.item.ShovelToolItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

public final class ExcavatorAbility {
    private static final Map<MineKey, Direction> BREAK_FACES = new HashMap<>();
    private static boolean breakingArea;

    private ExcavatorAbility() {
    }

    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getLevel().isClientSide()
                || event.getAction() != PlayerInteractEvent.LeftClickBlock.Action.START
                || event.getFace() == null
                || !(event.getEntity().getMainHandItem().getItem() instanceof ShovelToolItem)) {
            return;
        }

        BREAK_FACES.put(new MineKey(event.getEntity().getUUID(), event.getPos().immutable()), event.getFace());
    }

    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (breakingArea
                || !(event.getLevel() instanceof ServerLevel level)
                || !(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack shovel = player.getMainHandItem();
        if (!(shovel.getItem() instanceof ShovelToolItem) || !event.getState().is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return;
        }

        Direction face = BREAK_FACES.remove(new MineKey(player.getUUID(), event.getPos()));
        if (face == null) {
            face = fallbackFace(player);
        }

        List<BlockPos> targets = areaTargets(level, event.getPos(), face);
        if (!targets.isEmpty()) {
            breakArea(level, player, shovel, targets);
        }
    }

    private static List<BlockPos> areaTargets(ServerLevel level, BlockPos origin, Direction face) {
        List<BlockPos> targets = new ArrayList<>();
        for (BlockPos pos : BlockPos.betweenClosed(areaStart(origin, face), areaEnd(origin, face))) {
            BlockPos target = pos.immutable();
            if (target.equals(origin)) {
                continue;
            }

            BlockState state = level.getBlockState(target);
            if (!state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.getDestroySpeed(level, target) < 0.0F) {
                continue;
            }

            targets.add(target);
        }
        return targets;
    }

    private static void breakArea(ServerLevel level, ServerPlayer player, ItemStack shovel, List<BlockPos> targets) {
        breakingArea = true;
        try {
            for (BlockPos target : targets) {
                if (shovel.isEmpty() || !(shovel.getItem() instanceof ShovelToolItem)) {
                    return;
                }

                BlockState state = level.getBlockState(target);
                if (!state.is(BlockTags.MINEABLE_WITH_SHOVEL) || state.getDestroySpeed(level, target) < 0.0F) {
                    continue;
                }

                if (level.destroyBlock(target, true, player)) {
                    shovel.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
            }
        } finally {
            breakingArea = false;
        }
    }

    private static BlockPos areaStart(BlockPos origin, Direction face) {
        return switch (face.getAxis()) {
            case X -> origin.offset(0, -1, -1);
            case Y -> origin.offset(-1, 0, -1);
            case Z -> origin.offset(-1, -1, 0);
        };
    }

    private static BlockPos areaEnd(BlockPos origin, Direction face) {
        return switch (face.getAxis()) {
            case X -> origin.offset(0, 1, 1);
            case Y -> origin.offset(1, 0, 1);
            case Z -> origin.offset(1, 1, 0);
        };
    }

    private static Direction fallbackFace(ServerPlayer player) {
        if (Math.abs(player.getXRot()) > 60.0F) {
            return player.getXRot() > 0.0F ? Direction.UP : Direction.DOWN;
        }
        return player.getDirection().getOpposite();
    }

    private record MineKey(UUID playerId, BlockPos pos) {
    }
}
