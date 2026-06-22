package eu.zunix.tge_core.content.ability;

import eu.zunix.tge_core.content.item.AxeToolItem;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public final class TreecapitatorAbility {
    private static final int MAX_LOGS = 128;
    private static final int LOG_SEARCH_RADIUS = 1;
    private static final int LEAF_SEARCH_RADIUS = 4;
    private static final int LOG_BREAK_COOLDOWN_TICKS = 3;
    private static final Queue<TreeBreak> PENDING_TREES = new ArrayDeque<>();

    private TreecapitatorAbility() {
    }

    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level) || !(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        ItemStack axe = player.getMainHandItem();
        if (!(axe.getItem() instanceof AxeToolItem) || !event.getState().is(BlockTags.LOGS)) {
            return;
        }

        List<BlockPos> logs = connectedLogs(level, event.getPos());
        if (logs.size() <= 1 || !hasNearbyLeaves(level, logs)) {
            return;
        }

        logs.remove(event.getPos());
        if (!logs.isEmpty()) {
            PENDING_TREES.add(new TreeBreak(player.getUUID(), level.dimension(), new ArrayDeque<>(logs)));
        }
    }

    public static void onServerTick(ServerTickEvent.Post event) {
        int treesThisTick = PENDING_TREES.size();
        for (int i = 0; i < treesThisTick; i++) {
            TreeBreak tree = PENDING_TREES.poll();
            if (tree == null) {
                return;
            }

            if (tree.cooldownTicks() > 0) {
                tree.tickCooldown();
                PENDING_TREES.add(tree);
                continue;
            }

            ServerLevel level = event.getServer().getLevel(tree.dimension());
            ServerPlayer player = event.getServer().getPlayerList().getPlayer(tree.playerId());
            if (level == null || player == null || player.isRemoved()) {
                continue;
            }

            breakNextLog(level, player, tree);
            if (!tree.logs().isEmpty()) {
                PENDING_TREES.add(tree);
            }
        }
    }

    private static void breakNextLog(ServerLevel level, ServerPlayer player, TreeBreak tree) {
        ItemStack axe = player.getMainHandItem();
        if (!(axe.getItem() instanceof AxeToolItem)) {
            tree.logs().clear();
            return;
        }

        while (!tree.logs().isEmpty()) {
            BlockPos pos = tree.logs().poll();
            BlockState state = level.getBlockState(pos);
            if (!state.is(BlockTags.LOGS)) {
                continue;
            }

            if (level.destroyBlock(pos, true, player)) {
                axe.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                tree.setCooldownTicks(LOG_BREAK_COOLDOWN_TICKS);
            }
            return;
        }
    }

    private static List<BlockPos> connectedLogs(ServerLevel level, BlockPos origin) {
        List<BlockPos> logs = new ArrayList<>();
        Queue<BlockPos> open = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();
        open.add(origin.immutable());
        visited.add(origin.immutable());

        while (!open.isEmpty() && logs.size() < MAX_LOGS) {
            BlockPos current = open.poll();
            if (!level.getBlockState(current).is(BlockTags.LOGS)) {
                continue;
            }

            logs.add(current);
            for (BlockPos next : BlockPos.betweenClosed(
                    current.offset(-LOG_SEARCH_RADIUS, -1, -LOG_SEARCH_RADIUS),
                    current.offset(LOG_SEARCH_RADIUS, 1, LOG_SEARCH_RADIUS))) {
                BlockPos immutable = next.immutable();
                if (!immutable.equals(current) && visited.add(immutable)) {
                    open.add(immutable);
                }
            }
        }
        return logs;
    }

    private static boolean hasNearbyLeaves(ServerLevel level, List<BlockPos> logs) {
        for (BlockPos log : logs) {
            for (BlockPos pos : BlockPos.betweenClosed(
                    log.offset(-LEAF_SEARCH_RADIUS, -LEAF_SEARCH_RADIUS, -LEAF_SEARCH_RADIUS),
                    log.offset(LEAF_SEARCH_RADIUS, LEAF_SEARCH_RADIUS, LEAF_SEARCH_RADIUS))) {
                if (level.getBlockState(pos).is(BlockTags.LEAVES)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final class TreeBreak {
        private final UUID playerId;
        private final ResourceKey<Level> dimension;
        private final Queue<BlockPos> logs;
        private int cooldownTicks;

        private TreeBreak(UUID playerId, ResourceKey<Level> dimension, Queue<BlockPos> logs) {
            this.playerId = playerId;
            this.dimension = dimension;
            this.logs = logs;
        }

        private UUID playerId() {
            return playerId;
        }

        private ResourceKey<Level> dimension() {
            return dimension;
        }

        private Queue<BlockPos> logs() {
            return logs;
        }

        private int cooldownTicks() {
            return cooldownTicks;
        }

        private void setCooldownTicks(int cooldownTicks) {
            this.cooldownTicks = cooldownTicks;
        }

        private void tickCooldown() {
            cooldownTicks--;
        }
    }
}
