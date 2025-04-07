package net.zeus_32.tge_core.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RUBBER_PLACED_KEY = registerKey("rubber_tree_placed");

    public static final ResourceKey<PlacedFeature> ALUMINIUM_ORE_PLACED_KEY = registerKey("aluminium_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_ALUMINIUM_ORE_PLACED_KEY = registerKey("deepslate_aluminium_ore_placed");
    public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY = registerKey("lead_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_LEAD_ORE_PLACED_KEY = registerKey("deepslate_lead_ore_placed");
    public static final ResourceKey<PlacedFeature> NICKEL_ORE_PLACED_KEY = registerKey("nickel_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_NICKEL_ORE_PLACED_KEY = registerKey("deepslate_nickel_ore_placed");
    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_SILVER_ORE_PLACED_KEY = registerKey("deepslate_silver_ore_placed");
    public static final ResourceKey<PlacedFeature> OSMIUM_ORE_PLACED_KEY = registerKey("osmium_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_OSMIUM_ORE_PLACED_KEY = registerKey("deepslate_osmium_ore_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_TIN_ORE_PLACED_KEY = registerKey("deepslate_tin_ore_placed");
    public static final ResourceKey<PlacedFeature> ZINC_ORE_PLACED_KEY = registerKey("zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_ZINC_ORE_PLACED_KEY = registerKey("deepslate_zinc_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RUBBER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.RUBBER_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(0, 0.01f, 1), // 1% chance
                        ModBlocks.RUBBER_SAPLING.get()));

        register(context, ALUMINIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(48))));
        register(context, DEEPSLATE_ALUMINIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_ALUMINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-48))));

        register(context, LEAD_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(48))));
        register(context, DEEPSLATE_LEAD_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_LEAD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-48))));

        register(context, NICKEL_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));
        register(context, DEEPSLATE_NICKEL_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_NICKEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-64))));

        register(context, SILVER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32))));
        register(context, DEEPSLATE_SILVER_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_SILVER_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-64))));

        register(context, OSMIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OSMIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(48))));
        register(context, DEEPSLATE_OSMIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_OSMIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-64))));

        register(context, TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(32))));
        register(context, DEEPSLATE_TIN_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_TIN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-64))));

        register(context, ZINC_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));
        register(context, DEEPSLATE_ZINC_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_ZINC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-64))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(TGECore.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
