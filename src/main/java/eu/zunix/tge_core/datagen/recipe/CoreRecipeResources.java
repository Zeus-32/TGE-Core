package eu.zunix.tge_core.datagen.recipe;

import eu.zunix.tge_core.datagen.helper.DataPaths;
import eu.zunix.tge_core.datagen.helper.GeneratedData;
import eu.zunix.tge_core.datagen.helper.RecipeFile;
import eu.zunix.tge_core.registry.Tools;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;

final class CoreRecipeResources {
    private CoreRecipeResources() {
    }

    static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        RecipeFile.shaped("tge:unfired_clay_brick", 8)
                .pattern("CCC", "CMC", "CCC")
                .define('C', "minecraft:clay_ball")
                .define('M', "tge:brick_mold")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/unfired_clay_brick.json"));

        RecipeFile.shaped("tge:quartz_dust", 1)
                .pattern("S", "M")
                .define('S', "minecraft:sand")
                .defineTag('M', GeneratedData.MORTAR_TAG)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/quartz_dust.json"));

        RecipeFile.shaped("tge:flint_dust", 1)
                .pattern("F", "M")
                .define('F', "minecraft:flint")
                .defineTag('M', GeneratedData.MORTAR_TAG)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/flint_dust.json"));

        RecipeFile.shaped("tge:clay_dust", 1)
                .pattern("C", "M")
                .define('C', "minecraft:clay")
                .defineTag('M', GeneratedData.MORTAR_TAG)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/clay_dust.json"));

        RecipeFile.shaped("tge:brick_dust", 1)
                .pattern("B", "M")
                .define('B', "minecraft:bricks")
                .defineTag('M', GeneratedData.MORTAR_TAG)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/brick_dust.json"));

        RecipeFile.shapeless("tge:fire_clay_dust", 2)
                .ingredient("tge:clay_dust")
                .ingredient("tge:brick_dust")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/fire_clay_dust.json"));

        RecipeFile.shaped("tge:compressed_fire_brick", 8)
                .pattern("FFF", "FMF", "FFF")
                .define('F', "tge:fire_clay_dust")
                .define('M', "tge:brick_mold")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/compressed_fire_brick.json"));

        RecipeFile.smelting("tge:fire_brick")
                .ingredient("tge:compressed_fire_brick")
                .experience(0.2F)
                .cookingTime(400)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/smelting/fire_brick.json"));

        RecipeFile.shapeless("tge:glass_dust", 1)
                .ingredients("tge:quartz_dust", 8)
                .ingredient("tge:flint_dust")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/glass_dust.json"));

        toolRecipes(output, writes, cache);
        materialRecipes(output, writes, cache);
    }

    private static void toolRecipes(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        for (Tools.Entry tool : Tools.entries()) {
            String material = GeneratedData.tagMaterial(tool.material().id());
            RecipeFile recipe = switch (tool.type()) {
                case HAMMER -> toolRecipe(tool)
                        .pattern("III", "III", " S ")
                        .defineTag('I', "c:ingots/" + material)
                        .define('S', "minecraft:stick");
                case FILE -> toolRecipe(tool)
                        .pattern("P", "P", "S")
                        .defineTag('P', "c:plates/" + material)
                        .define('S', "minecraft:stick");
                case WRENCH -> toolRecipe(tool)
                        .pattern("PHP", " P ", " I ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTag('H', GeneratedData.HAMMER_TAG)
                        .defineTag('I', "c:ingots/" + material);
                case SCREWDRIVER -> toolRecipe(tool)
                        .pattern("  C", "FR ", "SH ")
                        .defineTag('C', "c:screws/" + material)
                        .defineTag('F', GeneratedData.FILE_TAG)
                        .defineTag('R', "c:rods/" + material)
                        .define('S', "minecraft:stick")
                        .defineTag('H', GeneratedData.HAMMER_TAG);
                case MORTAR -> toolRecipe(tool)
                        .pattern(" I ", "GIG", "GGG")
                        .defineTag('I', "c:ingots/" + material)
                        .define('G', "minecraft:stone");
                case SAW -> toolRecipe(tool)
                        .pattern("PPS", "FHS")
                        .defineTag('P', "c:plates/" + material)
                        .define('S', "minecraft:stick")
                        .defineTag('F', GeneratedData.FILE_TAG)
                        .defineTag('H', GeneratedData.HAMMER_TAG);
                default -> null;
            };

            if (recipe != null) {
                recipe.save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/" + tool.id() + ".json"));
            }
        }
    }

    private static void materialRecipes(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        for (String material : GeneratedData.ORE_AND_ALLOY_MATERIALS) {
            String tagMaterial = GeneratedData.tagMaterial(material);

            RecipeFile.shaped("ore_and_alloy:" + material + "_plate", 1)
                    .itemExists("ore_and_alloy:" + material + "_plate")
                    .pattern("H", "I", "I")
                    .defineTag('H', GeneratedData.HAMMER_TAG)
                    .defineTag('I', "c:ingots/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_plate.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_rod", 1)
                    .itemExists("ore_and_alloy:" + material + "_rod")
                    .pattern("F ", " I")
                    .defineTag('F', GeneratedData.FILE_TAG)
                    .defineTag('I', "c:ingots/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_rod.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_gear", 1)
                    .itemExists("ore_and_alloy:" + material + "_gear")
                    .pattern("RPR", "PHP", "RPR")
                    .defineTag('R', "c:rods/" + tagMaterial)
                    .defineTag('P', "c:plates/" + tagMaterial)
                    .defineTag('H', GeneratedData.HAMMER_TAG)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_gear.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_bolt", 2)
                    .itemExists("ore_and_alloy:" + material + "_bolt")
                    .pattern("S ", " R")
                    .defineTag('S', GeneratedData.SAW_TAG)
                    .defineTag('R', "c:rods/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_bolt.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_screw", 1)
                    .itemExists("ore_and_alloy:" + material + "_screw")
                    .pattern("WB", "B ")
                    .defineTag('W', GeneratedData.HAMMER_TAG)
                    .defineTag('B', "c:bolts/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_screw.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_dust", 1)
                    .itemExists("ore_and_alloy:" + material + "_dust")
                    .pattern("I")
                    .pattern("M")
                    .defineTag('M', GeneratedData.MORTAR_TAG)
                    .defineTag('I', "c:ingots/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_dust.json"));
        }
    }

    private static RecipeFile toolRecipe(Tools.Entry tool) {
        return RecipeFile.shaped("tge:" + tool.id(), 1);
    }
}
