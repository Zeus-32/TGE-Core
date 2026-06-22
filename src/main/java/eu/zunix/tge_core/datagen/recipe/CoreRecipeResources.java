package eu.zunix.tge_core.datagen.recipe;

import eu.zunix.tge_core.datagen.helper.DataPaths;
import eu.zunix.tge_core.datagen.helper.GeneratedData;
import eu.zunix.tge_core.datagen.helper.RecipeFile;
import eu.zunix.tge_core.content.tool.ToolType;
import eu.zunix.tge_core.registry.Tools;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;

final class CoreRecipeResources {
    private CoreRecipeResources() {
    }

    static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        RecipeFile.shaped("tge:brick_mold", 1)
                .pattern("S ", " P")
                .defineTool('S', ToolType.SAW)
                .defineTag('P', "minecraft:planks")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/brick_mold.json"));

        RecipeFile.shaped("tge:unfired_clay_brick", 8)
                .pattern("CCC", "CMC", "CCC")
                .define('C', "minecraft:clay_ball")
                .define('M', "tge:brick_mold")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/unfired_clay_brick.json"));

        RecipeFile.shaped("tge:quartz_dust", 1)
                .pattern("S", "M")
                .define('S', "minecraft:sand")
                .defineTool('M', ToolType.MORTAR)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/quartz_dust.json"));

        RecipeFile.shaped("tge:flint_dust", 1)
                .pattern("F", "M")
                .define('F', "minecraft:flint")
                .defineTool('M', ToolType.MORTAR)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/flint_dust.json"));

        RecipeFile.shaped("tge:clay_dust", 1)
                .pattern("C", "M")
                .define('C', "minecraft:clay")
                .defineTool('M', ToolType.MORTAR)
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/clay_dust.json"));

        RecipeFile.shaped("tge:brick_dust", 1)
                .pattern("B", "M")
                .define('B', "minecraft:bricks")
                .defineTool('M', ToolType.MORTAR)
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

        RecipeFile.shaped("tge:fire_bricks", 1)
                .pattern("BB", "BB")
                .define('B', "tge:fire_brick")
                .save(writes, cache, DataPaths.server(output, "tge", "recipe/crafting/fire_bricks.json"));

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
            String materialUnitTag = materialUnitTag(material);
            RecipeFile recipe = switch (tool.type()) {
                case HAMMER -> toolRecipe(tool)
                        .pattern("III", "III", " S ")
                        .defineTag('I', materialUnitTag)
                        .define('S', "minecraft:stick");
                case FILE -> toolRecipe(tool)
                        .pattern("P", "P", "S")
                        .defineTag('P', "c:plates/" + material)
                        .define('S', "minecraft:stick");
                case WRENCH -> toolRecipe(tool)
                        .pattern("PHP", " P ", " I ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTool('H', ToolType.HAMMER)
                        .defineTag('I', materialUnitTag);
                case SCREWDRIVER -> toolRecipe(tool)
                        .pattern("  C", "FR ", "SH ")
                        .defineTag('C', "c:screws/" + material)
                        .defineTool('F', ToolType.FILE)
                        .defineTag('R', "c:rods/" + material)
                        .define('S', "minecraft:stick")
                        .defineTool('H', ToolType.HAMMER);
                case MORTAR -> toolRecipe(tool)
                        .pattern(" I ", "GIG", "GGG")
                        .defineTag('I', materialUnitTag)
                        .define('G', "minecraft:stone");
                case SAW -> toolRecipe(tool)
                        .pattern("PPS", "FHS")
                        .defineTag('P', "c:plates/" + material)
                        .define('S', "minecraft:stick")
                        .defineTool('F', ToolType.FILE)
                        .defineTool('H', ToolType.HAMMER);
                case WIRE_CUTTERS -> toolRecipe(tool)
                        .pattern("P P", "HPS", "RCR")
                        .defineTag('P', "c:plates/" + material)
                        .defineTag('R', "c:rods/" + material)
                        .defineTag('C', "c:screws/" + material)
                        .defineTool('S', ToolType.SCREWDRIVER)
                        .defineTool('H', ToolType.HAMMER);

                case SWORD -> toolRecipe(tool)
                        .pattern(" P ", " P ", "HSW")
                        .defineTag('P', "c:plates/" + material)
                        .defineTool('H', ToolType.HAMMER)
                        .defineTool('W', ToolType.WRENCH)
                        .define('S', "minecraft:stick");
                case PICKAXE -> toolRecipe(tool)
                        .pattern("PIP", "HSW", " S ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTag('I', materialUnitTag)
                        .defineTool('W', ToolType.WRENCH)
                        .defineTool('H', ToolType.HAMMER)
                        .define('S', "minecraft:stick");
                case AXE -> toolRecipe(tool)
                        .pattern("PIH", "PSW", " S ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTag('I', materialUnitTag)
                        .defineTool('W', ToolType.WRENCH)
                        .defineTool('H', ToolType.HAMMER)
                        .define('S', "minecraft:stick");
                case SHOVEL -> toolRecipe(tool)
                        .pattern("HPW", " S ", " S ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTool('W', ToolType.WRENCH)
                        .defineTool('H', ToolType.HAMMER)
                        .define('S', "minecraft:stick");
                case HOE -> toolRecipe(tool)
                        .pattern("PIW", "HS ", " S ")
                        .defineTag('P', "c:plates/" + material)
                        .defineTag('I', materialUnitTag)
                        .defineTool('W', ToolType.WRENCH)
                        .defineTool('H', ToolType.HAMMER)
                        .define('S', "minecraft:stick");

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
            String materialUnitTag = materialUnitTag(tagMaterial);

            RecipeFile.shaped("ore_and_alloy:" + material + "_plate", 1)
                    .itemExists("ore_and_alloy:" + material + "_plate")
                    .pattern("H", "I", "I")
                    .defineTool('H', ToolType.HAMMER)
                    .defineTag('I', materialUnitTag)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_plate.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_rod", 1)
                    .itemExists("ore_and_alloy:" + material + "_rod")
                    .pattern("F ", " I")
                    .defineTool('F', ToolType.FILE)
                    .defineTag('I', materialUnitTag)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_rod.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_gear", 1)
                    .itemExists("ore_and_alloy:" + material + "_gear")
                    .pattern("RPR", "PHP", "RPR")
                    .defineTag('R', "c:rods/" + tagMaterial)
                    .defineTag('P', "c:plates/" + tagMaterial)
                    .defineTool('H', ToolType.HAMMER)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_gear.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_bolt", 2)
                    .itemExists("ore_and_alloy:" + material + "_bolt")
                    .pattern("S ", " R")
                    .defineTool('S', ToolType.SAW)
                    .defineTag('R', "c:rods/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_bolt.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_screw", 1)
                    .itemExists("ore_and_alloy:" + material + "_screw")
                    .pattern("WB", "B ")
                    .defineTool('W', ToolType.HAMMER)
                    .defineTag('B', "c:bolts/" + tagMaterial)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_screw.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_dust", 1)
                    .itemExists("ore_and_alloy:" + material + "_dust")
                    .pattern("I")
                    .pattern("M")
                    .defineTool('M', ToolType.MORTAR)
                    .defineTag('I', materialUnitTag)
                    .save(writes, cache, DataPaths.server(
                            output, "tge", "recipe/crafting/materials/" + material + "_dust.json"));
        }
    }

    private static RecipeFile toolRecipe(Tools.Entry tool) {
        return RecipeFile.shaped("tge:" + tool.id(), 1);
    }

    private static String materialUnitTag(String material) {
        return "diamond".equals(material) ? "c:gems/diamond" : "c:ingots/" + material;
    }
}
