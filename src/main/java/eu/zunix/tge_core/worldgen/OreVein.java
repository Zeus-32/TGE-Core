package eu.zunix.tge_core.worldgen;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public record OreVein(
        String name,
        int minY,
        int maxY,
        int weight,
        List<OreComponent> layers,
        List<ResourceKey<Level>> allowedDimensions,
        TagKey<Biome> requiredBiomeTag) {

    public OreVein {
        if (minY > maxY) {
            throw new IllegalArgumentException("OreVein minY must be <= maxY");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("OreVein weight must be > 0");
        }
        if (layers == null || layers.size() != 4) {
            throw new IllegalArgumentException("OreVein layers must contain exactly 4 components");
        }
    }

    public OreComponent primary() {
        return layers.get(0);
    }

    public OreComponent secondary() {
        return layers.get(1);
    }

    public OreComponent inBetween() {
        return layers.get(2);
    }

    public OreComponent sporadic() {
        return layers.get(3);
    }

    public boolean isAllowed(ServerLevel level, BlockPos centerPos) {
        if (!allowedDimensions.contains(level.dimension())) {
            return false;
        }

        return requiredBiomeTag == null || level.getBiome(centerPos).is(requiredBiomeTag);
    }
}
