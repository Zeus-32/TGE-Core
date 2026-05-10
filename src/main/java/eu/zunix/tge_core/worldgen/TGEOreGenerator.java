package eu.zunix.tge_core.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

public final class TGEOreGenerator {
    private static final int SECTOR_SIZE_CHUNKS = 3;
    private static final long SEED_CHUNK_X_MUL = 317L;
    private static final long SEED_CHUNK_Z_MUL = 13L;
    private static final int VEIN_RADIUS_MIN = 6;
    private static final int VEIN_RADIUS_MAX = 7;
    private static final int VEIN_VERTICAL_RADIUS = 8;

    private TGEOreGenerator() {
    }

    public static boolean generateChunkSliceForSector(ServerLevel level, ChunkAccess hostChunk) {
        ChunkPos chunkPos = hostChunk.getPos();
        int sectorChunkX = Math.floorDiv(chunkPos.x, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;
        int sectorChunkZ = Math.floorDiv(chunkPos.z, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;
        if (chunkPos.x != sectorChunkX || chunkPos.z != sectorChunkZ) {
            return false;
        }

        SectorGenerationContext context = computeSectorContext(level, sectorChunkX, sectorChunkZ);
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
                for (int y = minY; y <= maxY; y++) {
                    double ny = (y - context.centerY()) / (double) context.verticalRadius();
                    double distance = nx2 + nz2 + ny * ny;
                    if (distance > 1.0D) {
                        continue;
                    }

                    OreComponent component = componentForY(context, x, y, z);
                    if (random01(context.seed(), x, y, z, 0) >= component.density()) {
                        continue;
                    }

                    mutablePos.set(x, y, z);
                    BlockState current = hostChunk.getBlockState(mutablePos);
                    if (!isSafeReplaceTarget(current) || !isEnclosed(hostChunk, mutablePos)) {
                        continue;
                    }

                    Block replacement = resolveBlock(component.blockId());
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
            placeSmallSurfaceOres(hostChunk, context, chunkPos);
        }

        return placedAny;
    }

    public static List<SectorDebugInfo> listVeins(ServerLevel level, BlockPos origin, int radiusInChunks) {
        List<SectorDebugInfo> out = new ArrayList<>();
        int originChunkX = origin.getX() >> 4;
        int originChunkZ = origin.getZ() >> 4;

        int minSectorX = Math.floorDiv(originChunkX - radiusInChunks, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;
        int maxSectorX = Math.floorDiv(originChunkX + radiusInChunks, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;
        int minSectorZ = Math.floorDiv(originChunkZ - radiusInChunks, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;
        int maxSectorZ = Math.floorDiv(originChunkZ + radiusInChunks, SECTOR_SIZE_CHUNKS) * SECTOR_SIZE_CHUNKS;

        for (int cx = minSectorX; cx <= maxSectorX; cx += SECTOR_SIZE_CHUNKS) {
            for (int cz = minSectorZ; cz <= maxSectorZ; cz += SECTOR_SIZE_CHUNKS) {
                SectorGenerationContext context = computeSectorContext(level, cx, cz);
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

    private static SectorGenerationContext computeSectorContext(ServerLevel level, int centerChunkX, int centerChunkZ) {
        long seed = level.getSeed() + centerChunkX * SEED_CHUNK_X_MUL + centerChunkZ * SEED_CHUNK_Z_MUL;
        RandomSource random = RandomSource.create(seed);

        int centerX = centerChunkX * 16 + 8;
        int centerZ = centerChunkZ * 16 + 8;
        BlockPos centerPos = new BlockPos(centerX, 0, centerZ);

        OreVein selected = OreVeinRegistry.pick(random, level, centerPos);
        if (selected == null) {
            return null;
        }

        int centerY = Mth.nextInt(random, selected.minY(), selected.maxY());
        int surfaceY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, centerX, centerZ);
        if (centerY > surfaceY) {
            return null;
        }

        int horizontalRadiusX = Mth.nextInt(random, VEIN_RADIUS_MIN, VEIN_RADIUS_MAX);
        int horizontalRadiusZ = Mth.nextInt(random, VEIN_RADIUS_MIN, VEIN_RADIUS_MAX);
        int verticalRadius = VEIN_VERTICAL_RADIUS;

        return new SectorGenerationContext(
                seed,
                centerChunkX,
                centerChunkZ,
                centerX,
                centerZ,
                centerY,
                horizontalRadiusX,
                horizontalRadiusZ,
                verticalRadius,
                selected,
                random);
    }

    private static OreComponent componentForY(SectorGenerationContext context, int x, int y, int z) {
        int relY = y - context.centerY();
        if (relY >= 2) {
            return context.vein().top();
        }
        if (relY <= -2) {
            return context.vein().bottom();
        }
        if (random01(context.seed(), x, y, z, 1) < 0.5D) {
            return context.vein().between();
        }
        return context.vein().main();
    }

    private static boolean isSafeReplaceTarget(BlockState state) {
        return state.is(Blocks.STONE) || state.is(Blocks.DEEPSLATE);
    }

    private static boolean isEnclosed(ChunkAccess chunk, BlockPos pos) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (Direction direction : Direction.values()) {
            checkPos.set(
                    pos.getX() + direction.getStepX(),
                    pos.getY() + direction.getStepY(),
                    pos.getZ() + direction.getStepZ());
            BlockState neighbor = chunk.getBlockState(checkPos);
            if (neighbor.isAir() || !neighbor.getFluidState().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Block resolveBlock(String blockId) {
        return Optional.ofNullable(ResourceLocation.tryParse(blockId))
                .flatMap(id -> BuiltInRegistries.BLOCK.getOptional(id))
                .orElse(Blocks.AIR);
    }

    private static void placeSmallSurfaceOres(ChunkAccess chunk, SectorGenerationContext context, ChunkPos hostChunk) {
        Block smallOreBlock = resolveBlock(context.vein().main().blockId());
        if (smallOreBlock == Blocks.AIR) {
            return;
        }

        RandomSource random = RandomSource.create(context.seed() ^ 0x5DEECE66DL);
        int count = 5 + random.nextInt(6);
        int minX = hostChunk.getMinBlockX();
        int maxX = hostChunk.getMaxBlockX();
        int minZ = hostChunk.getMinBlockZ();
        int maxZ = hostChunk.getMaxBlockZ();

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int placed = 0;
        int attempts = 0;
        int maxAttempts = count * 32;
        while (placed < count && attempts++ < maxAttempts) {
            int x = context.centerX() + random.nextInt(33) - 16;
            int z = context.centerZ() + random.nextInt(33) - 16;
            if (x < minX || x > maxX || z < minZ || z > maxZ) {
                continue;
            }
            int localX = x - minX;
            int localZ = z - minZ;
            int surfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, localX, localZ);
            int y = surfaceY - 1;
            if (y < 60 || y > 80) {
                continue;
            }

            mutable.set(x, y, z);
            BlockState state = chunk.getBlockState(mutable);
            if (!isSafeReplaceTarget(state)) {
                continue;
            }
            if (!isEnclosed(chunk, mutable)) {
                continue;
            }

            BlockState previous = chunk.setBlockState(mutable, smallOreBlock.defaultBlockState(), false);
            if (previous != null) {
                placed++;
            }
        }
    }

    private static double random01(long sectorSeed, int x, int y, int z, int salt) {
        long hash = sectorSeed;
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

    private record SectorGenerationContext(
            long seed,
            int centerChunkX,
            int centerChunkZ,
            int centerX,
            int centerZ,
            int centerY,
            int horizontalRadiusX,
            int horizontalRadiusZ,
            int verticalRadius,
            OreVein vein,
            RandomSource random) {
    }

    public record SectorDebugInfo(int x, int z, String veinName, int centerY) {
    }
}
