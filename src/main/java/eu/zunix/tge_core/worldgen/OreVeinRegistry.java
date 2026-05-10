package eu.zunix.tge_core.worldgen;

import java.util.ArrayList;
import java.util.List;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public final class OreVeinRegistry {
    private static final List<OreVein> VEINS = createVeins();

    private OreVeinRegistry() {
    }

    public static List<OreVein> all() {
        return VEINS;
    }

    public static OreVein pick(RandomSource random, ServerLevel level, BlockPos centerPos) {
        List<OreVein> allowed = new ArrayList<>();
        int totalWeight = 0;

        for (OreVein vein : VEINS) {
            if (vein.isAllowed(level, centerPos)) {
                allowed.add(vein);
                totalWeight += vein.weight();
            }
        }

        if (allowed.isEmpty()) {
            return null;
        }

        int roll = Mth.nextInt(random, 1, totalWeight);
        int current = 0;
        for (OreVein vein : allowed) {
            current += vein.weight();
            if (roll <= current) {
                return vein;
            }
        }

        return allowed.get(allowed.size() - 1);
    }

    private static List<OreVein> createVeins() {
        List<OreVein> list = new ArrayList<>();

        for (OreMaterial material : OreMaterial.values()) {
            list.add(new OreVein(
                    material.displayName() + " Vein",
                    minYFor(material),
                    maxYFor(material),
                    weightFor(material),
                    List.of(
                            new OreComponent(TGECore.MODID + ":" + material.oreId(), 0.20),
                            new OreComponent(TGECore.MODID + ":" + material.oreId(), 0.42),
                            new OreComponent(TGECore.MODID + ":" + material.deepslateOreId(), 0.70),
                            new OreComponent(TGECore.MODID + ":" + material.deepslateOreId(), 0.34)),
                    List.of(Level.OVERWORLD),
                    null));
        }

        return List.copyOf(list);
    }

    private static int weightFor(OreMaterial material) {
        return switch (material) {
            case IRON -> 60;
            case COPPER -> 45;
            case GOLD -> 35;
            case TIN, NICKEL, ZINC, LEAD, SILVER -> 28;
            default -> 14;
        };
    }

    private static int minYFor(OreMaterial material) {
        return switch (material) {
            case GOLD, PLATINUM, OSMIUM, URANIUM -> -56;
            case IRON, NICKEL, COBALT, TUNGSTEN -> -48;
            case COPPER, TIN, ZINC, SILICON -> -24;
            default -> -40;
        };
    }

    private static int maxYFor(OreMaterial material) {
        return switch (material) {
            case COPPER -> 96;
            case IRON -> 72;
            case TIN, ZINC, SILICON -> 84;
            case GOLD, PLATINUM, OSMIUM, URANIUM -> 24;
            default -> 56;
        };
    }
}
