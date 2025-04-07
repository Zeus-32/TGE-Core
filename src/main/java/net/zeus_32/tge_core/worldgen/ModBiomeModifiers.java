package net.zeus_32.tge_core.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.zeus_32.tge_core.TGECore;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_RUBBER = registerKey("add_tree_rubber");

    public static final ResourceKey<BiomeModifier> ADD_ALUMINIUM_ORE = registerKey("add_ore_aluminium");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_ALUMINIUM_ORE = registerKey("add_ore_deepslate_aluminium");
    public static final ResourceKey<BiomeModifier> ADD_LEAD_ORE = registerKey("add_ore_lead");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_LEAD_ORE = registerKey("add_ore_deepslate_lead");
    public static final ResourceKey<BiomeModifier> ADD_NICKEL_ORE = registerKey("add_ore_nickel");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_NICKEL_ORE = registerKey("add_ore_deepslate_nickel");
    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE = registerKey("add_ore_silver");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_SILVER_ORE = registerKey("add_ore_deepslate_silver");
    public static final ResourceKey<BiomeModifier> ADD_OSMIUM_ORE = registerKey("add_ore_osmium");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_OSMIUM_ORE = registerKey("add_ore_deepslate_osmium");
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_ore_tin");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_TIN_ORE = registerKey("add_ore_deepslate_tin");
    public static final ResourceKey<BiomeModifier> ADD_ZINC_ORE = registerKey("add_ore_zinc");
    public static final ResourceKey<BiomeModifier> ADD_DEEPSLATE_ZINC_ORE = registerKey("add_ore_deepslate_zinc");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomeLookup = context.lookup(Registries.BIOME);

        HolderSet<Biome> overworldBiomes = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD);

        context.register(ADD_TREE_RUBBER, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RUBBER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_ALUMINIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ALUMINIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_ALUMINIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_ALUMINIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_LEAD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_LEAD_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_LEAD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_NICKEL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NICKEL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_NICKEL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_NICKEL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_SILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_SILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_OSMIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OSMIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_OSMIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_OSMIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_TIN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_TIN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_TIN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_ZINC_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ZINC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_DEEPSLATE_ZINC_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                overworldBiomes,
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.DEEPSLATE_ZINC_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(TGECore.MODID, name));
    }
}
