package eu.zunix.tge_core.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

public final class TGEOreGenerator {
    private static final int ORE_CHUNK_STEP = 3;
    private static final long SEED_CHUNK_X_MUL = 317L;
    private static final long SEED_CHUNK_Z_MUL = 13L;
    private static final int VEIN_HORIZONTAL_RADIUS = 7;
    private static final int VEIN_VERTICAL_RADIUS = 6;
    private static final int SURFACE_MARGIN = 8;
    private static final int MIN_SURFACE_INDICATORS = 1;
    private static final int MAX_SURFACE_INDICATORS = 2;
    private static final double BODY_DENSITY_CORE = 0.74D;
    private static final double BODY_DENSITY_EDGE = 0.44D;

    private static final double PRIMARY_RATIO = 0.30D;
    private static final double SECONDARY_RATIO = 0.15D;
    private static final double IN_BETWEEN_RATIO = 0.10D;
    private static final double SPORADIC_RATIO = 0.05D;
    private static final double FILLER_RATIO = 0.40D;

    private static final double SECONDARY_THRESHOLD = PRIMARY_RATIO + SECONDARY_RATIO;
    private static final double IN_BETWEEN_THRESHOLD = SECONDARY_THRESHOLD + IN_BETWEEN_RATIO;
    private static final double SPORADIC_THRESHOLD = IN_BETWEEN_THRESHOLD + SPORADIC_RATIO;
    private static final double FILLER_THRESHOLD = SPORADIC_THRESHOLD + FILLER_RATIO;

    private static final Map<String, Block> BLOCK_CACHE = new ConcurrentHashMap<>();

    private TGEOreGenerator() {
    }

    public static boolean generateChunkSliceForSector(ServerLevel level, ChunkAccess hostChunk) {
        ChunkPos chunkPos = hostChunk.getPos();
        if (!isOreChunk(chunkPos)) {
            return false;
        }

        ChunkGenerationContext context = computeChunkContext(level, chunkPos);
        if (context == null) {
            return false;
        }

        int minChunkX = chunkPos.getMinBlockX();
        int maxChunkX = chunkPos.getMaxBlockX();
        int minChunkZ = chunkPos.getMinBlockZ();
        int maxChunkZ = chunkPos.getMaxBlockZ();

        int minX = Math.max(minChunkX, context.centerX() - context.horizontalRadiusX());
        int maxX = Math.min(maxChunkX, context.centerX() + context.horizontalRadiusX());
        int minZ = Math.max(minChunkZ, context.centerZ() - context.horizontalRadiusZ());
        int maxZ = Math.min(maxChunkZ, context.centerZ() + context.horizontalRadiusZ());
        int minY = Math.max(level.getMinBuildHeight(), context.centerY() - context.verticalRadius());
        int maxY = Math.min(level.getMaxBuildHeight() - 1, context.centerY() + context.verticalRadius());

        boolean placedAny = false;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; x++) {
            double nx = (x - context.centerX()) / (double) context.horizontalRadiusX();
            double nx2 = nx * nx;
            for (int z = minZ; z <= maxZ; z++) {
                double nz = (z - context.centerZ()) / (double) context.horizontalRadiusZ();
                double nz2 = nz * nz;
                int surfaceY = level.dimension().equals(Level.OVERWORLD)
                        ? level.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z)
                        : Integer.MAX_VALUE;
                for (int y = minY; y <= maxY; y++) {
                    double ny = (y - context.centerY()) / (double) context.verticalRadius();
                    double distance = nx2 + nz2 + ny * ny;
                    if (distance > 1.0D) {
                        continue;
                    }
                    if (level.dimension().equals(Level.OVERWORLD) && y > surfaceY - SURFACE_MARGIN) {
                        continue;
                    }

                    double bodyDensity = Mth.lerp(distance, BODY_DENSITY_CORE, BODY_DENSITY_EDGE);
                    if (random01(context.seed(), x, y, z, 11) > bodyDensity) {
                        continue;
                    }

                    mutablePos.set(x, y, z);
                    BlockState current = hostChunk.getBlockState(mutablePos);
                    if (!isSafeReplaceTarget(current, level.dimension())) {
                        continue;
                    }

                    String targetBlockId = pickBlockIdForPoint(context, x, y, z);
                    Block replacement = resolveBlock(targetBlockId, level, y);
                    if (replacement == Blocks.AIR) {
                        continue;
                    }

                    BlockState previous = hostChunk.setBlockState(mutablePos, replacement.defaultBlockState(), false);
                    if (previous != null && previous.getBlock() != replacement) {
                        placedAny = true;
                    }
                }
            }
        }

        if (placedAny) {
            placeSurfaceIndicators(level, hostChunk, chunkPos, context);
            OreChunkMetadata.get(level).setVeinInfo(chunkPos, veinInfo(context));
        }

        return placedAny;
    }

    public static OreChunkMetadata.VeinInfo getVeinInfoForChunk(ServerLevel level, ChunkPos chunkPos) {
        return OreChunkMetadata.get(level).getVeinInfo(chunkPos);
    }

    public static int scrubForeignOres(ServerLevel level, ChunkAccess chunk) {
        int removed = 0;
        int minY = level.getMinBuildHeight();
        int maxY = level.getMaxBuildHeight() - 1;
        int minX = chunk.getPos().getMinBlockX();
        int maxX = chunk.getPos().getMaxBlockX();
        int minZ = chunk.getPos().getMinBlockZ();
        int maxZ = chunk.getPos().getMaxBlockZ();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY; y <= maxY; y++) {
                    pos.set(x, y, z);
                    BlockState state = chunk.getBlockState(pos);
                    if (state.isAir()) {
                        continue;
                    }

                    ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
                    if (id.getNamespace().equals("tge")) {
                        continue;
                    }
                    if (!isSuppressedOrePath(id.getPath())) {
                        continue;
                    }

                    Block replacement = hostFillerBlock(level.dimension(), y);
                    if (state.getBlock() == replacement) {
                        continue;
                    }
                    chunk.setBlockState(pos, replacement.defaultBlockState(), false);
                    removed++;
                }
            }
        }

        return removed;
    }

    public static List<SectorDebugInfo> listVeins(ServerLevel level, BlockPos origin, int radiusInChunks) {
        List<SectorDebugInfo> out = new ArrayList<>();
        int originChunkX = origin.getX() >> 4;
        int originChunkZ = origin.getZ() >> 4;

        for (int cx = originChunkX - radiusInChunks; cx <= originChunkX + radiusInChunks; cx++) {
            for (int cz = originChunkZ - radiusInChunks; cz <= originChunkZ + radiusInChunks; cz++) {
                ChunkPos candidate = new ChunkPos(cx, cz);
                if (!isOreChunk(candidate)) {
                    continue;
                }
                ChunkGenerationContext context = computeChunkContext(level, candidate);
                if (context != null) {
                    out.add(new SectorDebugInfo(
                            context.centerX(),
                            context.centerZ(),
                            context.vein().name(),
                            context.centerY()));
                }
            }
        }

        return out;
    }

    private static boolean isOreChunk(ChunkPos chunkPos) {
        return Math.floorMod(chunkPos.x, ORE_CHUNK_STEP) == 0
                && Math.floorMod(chunkPos.z, ORE_CHUNK_STEP) == 0;
    }

    private static ChunkGenerationContext computeChunkContext(ServerLevel level, ChunkPos chunkPos) {
        long seed = level.getSeed() + chunkPos.x * SEED_CHUNK_X_MUL + chunkPos.z * SEED_CHUNK_Z_MUL;
        RandomSource random = RandomSource.create(seed);

        int centerX = chunkPos.getMinBlockX() + 8;
        int centerZ = chunkPos.getMinBlockZ() + 8;
        BlockPos centerPos = new BlockPos(centerX, 0, centerZ);

        OreVein selected = OreVeinRegistry.pick(random, level, centerPos);
        if (selected == null) {
            return null;
        }

        int veinMinY = selected.minY();
        int veinMaxY = selected.maxY();
        if (level.dimension().equals(Level.OVERWORLD)) {
            int surfaceY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, centerX, centerZ);
            veinMaxY = Math.min(veinMaxY, surfaceY - SURFACE_MARGIN);
            if (veinMaxY < veinMinY) {
                return null;
            }
        }

        int centerY = Mth.nextInt(random, veinMinY, veinMaxY);
        int horizontalRadiusX = VEIN_HORIZONTAL_RADIUS;
        int horizontalRadiusZ = VEIN_HORIZONTAL_RADIUS;

        return new ChunkGenerationContext(
                seed,
                chunkPos,
                level.dimension(),
                centerX,
                centerZ,
                centerY,
                horizontalRadiusX,
                horizontalRadiusZ,
                VEIN_VERTICAL_RADIUS,
                selected);
    }

    private static String pickBlockIdForPoint(ChunkGenerationContext context, int x, int y, int z) {
        double roll = random01(context.seed(), x, y, z, 1);
        if (roll < PRIMARY_RATIO) {
            return context.vein().primary().blockId();
        }
        if (roll < SECONDARY_THRESHOLD) {
            return context.vein().secondary().blockId();
        }
        if (roll < IN_BETWEEN_THRESHOLD) {
            return context.vein().inBetween().blockId();
        }
        if (roll < SPORADIC_THRESHOLD) {
            return context.vein().sporadic().blockId();
        }
        if (roll < FILLER_THRESHOLD) {
            return fillerBlockId(context.dimension(), y);
        }
        return context.vein().primary().blockId();
    }

    private static String fillerBlockId(ResourceKey<Level> dimension, int y) {
        return BuiltInRegistries.BLOCK.getKey(hostFillerBlock(dimension, y)).toString();
    }

    private static void placeSurfaceIndicators(
            ServerLevel level,
            ChunkAccess hostChunk,
            ChunkPos chunkPos,
            ChunkGenerationContext context) {
        if (!level.dimension().equals(Level.OVERWORLD)) {
            return;
        }

        RandomSource random = RandomSource.create(context.seed() ^ 0x5DEECE66DL);
        int target = Mth.nextInt(random, MIN_SURFACE_INDICATORS, MAX_SURFACE_INDICATORS);
        int placed = 0;
        int attempts = 0;
        int maxAttempts = target * 20;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos above = new BlockPos.MutableBlockPos();

        while (placed < target && attempts++ < maxAttempts) {
            int x = Mth.nextInt(
                    random,
                    Math.max(chunkPos.getMinBlockX(), context.centerX() - 5),
                    Math.min(chunkPos.getMaxBlockX(), context.centerX() + 5));
            int z = Mth.nextInt(
                    random,
                    Math.max(chunkPos.getMinBlockZ(), context.centerZ() - 5),
                    Math.min(chunkPos.getMaxBlockZ(), context.centerZ() + 5));
            int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) - 1;
            if (y <= level.getMinBuildHeight() + 1) {
                continue;
            }
            if (y - context.centerY() > 28) {
                continue;
            }

            mutable.set(x, y, z);
            above.set(x, y + 1, z);
            if (!level.isEmptyBlock(above)) {
                continue;
            }

            BlockState current = hostChunk.getBlockState(mutable);
            if (!isSafeReplaceTarget(current, level.dimension())) {
                continue;
            }

            Block replacement = resolveBlock(context.vein().primary().blockId(), level, y);
            if (replacement == Blocks.AIR) {
                continue;
            }

            hostChunk.setBlockState(mutable, replacement.defaultBlockState(), false);
            placed++;
        }
    }

    private static boolean isSafeReplaceTarget(BlockState state, ResourceKey<Level> dimension) {
        if (dimension.equals(Level.NETHER)) {
            return state.is(BlockTags.BASE_STONE_NETHER);
        }
        if (dimension.equals(Level.END)) {
            return state.is(Blocks.END_STONE);
        }
        return state.is(BlockTags.STONE_ORE_REPLACEABLES)
                || state.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    }

    private static boolean isSuppressedOrePath(String path) {
        return path.endsWith("_ore") || path.equals("ancient_debris");
    }

    private static Block hostFillerBlock(ResourceKey<Level> dimension, int y) {
        if (dimension.equals(Level.NETHER)) {
            return Blocks.NETHERRACK;
        }
        if (dimension.equals(Level.END)) {
            return Blocks.END_STONE;
        }
        return y < 0 ? Blocks.DEEPSLATE : Blocks.STONE;
    }

    private static Block resolveBlock(String blockId, ServerLevel level, int y) {
        String resolvedId = adaptBlockForDepth(blockId, level.dimension(), y);
        return BLOCK_CACHE.computeIfAbsent(resolvedId, key -> Optional.ofNullable(ResourceLocation.tryParse(key))
                .flatMap(id -> BuiltInRegistries.BLOCK.getOptional(id))
                .orElse(Blocks.AIR));
    }

    private static String adaptBlockForDepth(String blockId, ResourceKey<Level> dimension, int y) {
        if (y >= 0 || !dimension.equals(Level.OVERWORLD)) {
            return blockId;
        }

        ResourceLocation id = ResourceLocation.tryParse(blockId);
        if (id == null) {
            return blockId;
        }

        String path = id.getPath();
        if (!path.endsWith("_ore")
                || path.startsWith("deepslate_")
                || path.startsWith("nether_")
                || path.startsWith("end_")) {
            return blockId;
        }

        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "deepslate_" + path).toString();
    }

    private static OreChunkMetadata.VeinInfo veinInfo(ChunkGenerationContext context) {
        return new OreChunkMetadata.VeinInfo(
                context.vein().name(),
                context.centerY(),
                context.chunkPos().x,
                context.chunkPos().z);
    }

    private static double random01(long chunkSeed, int x, int y, int z, int salt) {
        long hash = chunkSeed;
        hash = mix(hash ^ (x * 341873128712L));
        hash = mix(hash ^ (z * 132897987541L));
        hash = mix(hash ^ (y * 42317861L));
        hash = mix(hash ^ (salt * 0x9E3779B97F4A7C15L));
        long bits = (hash >>> 11) & ((1L << 53) - 1);
        return bits / (double) (1L << 53);
    }

    private static long mix(long value) {
        value ^= (value >>> 33);
        value *= 0xff51afd7ed558ccdl;
        value ^= (value >>> 33);
        value *= 0xc4ceb9fe1a85ec53l;
        value ^= (value >>> 33);
        return value;
    }

    private record ChunkGenerationContext(
            long seed,
            ChunkPos chunkPos,
            ResourceKey<Level> dimension,
            int centerX,
            int centerZ,
            int centerY,
            int horizontalRadiusX,
            int horizontalRadiusZ,
            int verticalRadius,
            OreVein vein) {
    }

    public record SectorDebugInfo(int x, int z, String veinName, int centerY) {
    }
}
