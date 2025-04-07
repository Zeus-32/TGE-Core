package net.zeus_32.tge_core.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int pBaseCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pBaseCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int pBaseCount, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pBaseCount), pHeightRange);
    }
}
