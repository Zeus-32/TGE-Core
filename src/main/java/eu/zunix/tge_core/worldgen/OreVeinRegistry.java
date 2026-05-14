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
    private static final double PRIMARY_DENSITY = 0.74D;
    private static final double SECONDARY_DENSITY = 0.46D;
    private static final double IN_BETWEEN_DENSITY = 0.58D;
    private static final double SPORADIC_DENSITY = 0.15D;

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

        list.add(new OreVein(
                "Iron Vein",
                10,
                60,
                52,
                List.of(
                        new OreComponent(ore(OreMaterial.IRON), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.NICKEL), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.ZINC), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.SILVER), SPORADIC_DENSITY)),
                List.of(Level.OVERWORLD),
                null));

        list.add(new OreVein(
                "Copper Vein",
                30,
                70,
                50,
                List.of(
                        new OreComponent(ore(OreMaterial.COPPER), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.ZINC), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.ALUMINIUM), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.GOLD), SPORADIC_DENSITY)),
                List.of(Level.OVERWORLD),
                null));
        list.add(new OreVein(
                "Lead Vein",
                -15,
                40,
                30,
                List.of(
                        new OreComponent(ore(OreMaterial.LEAD), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.SILVER), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.NICKEL), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.GOLD), SPORADIC_DENSITY)),
                List.of(Level.OVERWORLD),
                null));

        list.add(new OreVein(
                "Gold Vein",
                -32,
                20,
                24,
                List.of(
                        new OreComponent(ore(OreMaterial.GOLD), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.IRON), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.COPPER), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.DIAMOND), SPORADIC_DENSITY)),
                List.of(Level.OVERWORLD),
                null));

        list.add(new OreVein(
                "Gem Vein",
                -64,
                -20,
                14,
                List.of(
                        new OreComponent(ore(OreMaterial.DIAMOND), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.EMERALD), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.DIAMOND), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.EMERALD), SPORADIC_DENSITY)),
                List.of(Level.OVERWORLD),
                null));

        list.add(new OreVein(
                "Osmium Vein",
                10,
                60,
                10,
                List.of(
                        new OreComponent(ore(OreMaterial.OSMIUM), PRIMARY_DENSITY),
                        new OreComponent(ore(OreMaterial.NICKEL), SECONDARY_DENSITY),
                        new OreComponent(ore(OreMaterial.SILVER), IN_BETWEEN_DENSITY),
                        new OreComponent(ore(OreMaterial.GOLD), SPORADIC_DENSITY)),
                List.of(Level.END),
                null));

        return List.copyOf(list);
    }

    private static String ore(OreMaterial material) {
        return TGECore.MODID + ":" + material.oreId();
    }

}
