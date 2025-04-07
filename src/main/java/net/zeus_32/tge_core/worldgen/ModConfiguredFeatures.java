package net.zeus_32.tge_core.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_KEY = registerKey("rubber_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINIUM_ORE_KEY = registerKey("aluminium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_ALUMINIUM_ORE_KEY = registerKey("deepslate_aluminium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = registerKey("lead_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_LEAD_ORE_KEY = registerKey("deepslate_lead_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NICKEL_ORE_KEY = registerKey("nickel_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_NICKEL_ORE_KEY = registerKey("deepslate_nickel_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_SILVER_ORE_KEY = registerKey("deepslate_silver_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OSMIUM_ORE_KEY = registerKey("osmium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_OSMIUM_ORE_KEY = registerKey("deepslate_osmium_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_TIN_ORE_KEY = registerKey("deepslate_tin_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZINC_ORE_KEY = registerKey("zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_ZINC_ORE_KEY = registerKey("deepslate_zinc_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> AluminiumOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> LeadOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.LEAD_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> NickelOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.NICKEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> SilverOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> OsmiumOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.OSMIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_OSMIUM_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> TinOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> ZincOres = List.of(
                OreConfiguration.target(stoneReplacables, ModBlocks.ZINC_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_ZINC_ORE.get().defaultBlockState()));

        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(AluminiumOres, 5));
        register(context, DEEPSLATE_ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(AluminiumOres, 5));

        register(context, LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(LeadOres, 5));
        register(context, DEEPSLATE_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(LeadOres, 5));

        register(context, NICKEL_ORE_KEY, Feature.ORE, new OreConfiguration(NickelOres, 6));
        register(context, DEEPSLATE_NICKEL_ORE_KEY, Feature.ORE, new OreConfiguration(NickelOres, 6));

        register(context, SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(SilverOres, 4));
        register(context, DEEPSLATE_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(SilverOres, 4));

        register(context, OSMIUM_ORE_KEY, Feature.ORE, new OreConfiguration(OsmiumOres, 6));
        register(context, DEEPSLATE_OSMIUM_ORE_KEY, Feature.ORE, new OreConfiguration(OsmiumOres, 6));

        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(TinOres, 6));
        register(context, DEEPSLATE_TIN_ORE_KEY, Feature.ORE, new OreConfiguration(TinOres, 6));

        register(context, ZINC_ORE_KEY, Feature.ORE, new OreConfiguration(ZincOres, 6));
        register(context, DEEPSLATE_ZINC_ORE_KEY, Feature.ORE, new OreConfiguration(ZincOres, 6));


        register(context, RUBBER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.RUBBER_LOG.get()),
                new StraightTrunkPlacer(5, 3, 0),
                BlockStateProvider.simple(ModBlocks.RUBBER_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TGECore.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                         ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
