package eu.zunix.tge_core.datagen;

import com.google.gson.JsonObject;
import eu.zunix.tge_core.content.tool.TGEToolType;
import eu.zunix.tge_core.registry.TGETools;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

public final class TGEGeneratedResources implements DataProvider {
    private static final String[] ITEMS = {
            "glass_dust",
            "flint_dust",
            "clay_dust",
            "brick_dust",
            "fire_clay_dust",
            "fire_brick",
            "unfired_clay_brick",
            "brick_mold"
    };
    private static final String[] ORE_AND_ALLOY_MATERIALS = {
            "aluminium",
            "antimony",
            "brass",
            "bronze",
            "chromium",
            "cobalt",
            "constantan",
            "copper",
            "cupronickel",
            "electrum",
            "enderium",
            "gold",
            "invar",
            "iridium",
            "iron",
            "lead",
            "lithium",
            "lumium",
            "naquadah",
            "nickel",
            "osmium",
            "platinum",
            "red_alloy",
            "silver",
            "soul_infused",
            "stainless_steel",
            "steel",
            "tin",
            "titanium",
            "tungsten",
            "uranium",
            "wrought_iron",
            "zinc"
    };
    private static final String IRON_INGOT_TAG = "c:ingots/iron";
    private static final String IRON_PLATE_TAG = "c:plates/iron";
    private static final String IRON_ROD_TAG = "c:rods/iron";
    private static final String IRON_SCREW_TAG = "c:screws/iron";
    private static final String HAMMER_TAG = "tge:tools/hammers";
    private static final String FILE_TAG = "tge:tools/files";
    private final PackOutput output;

    public TGEGeneratedResources(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();
        for (String item : ITEMS) {
            model(writes, cache, item, "generated");
        }
        for (TGETools.Entry tool : TGETools.entries()) {
            model(writes, cache, tool.id(), "handheld");
        }
        language(writes, cache, "en_us", english());
        toolTags(writes, cache);
        recipes(writes, cache);
        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private void model(List<CompletableFuture<?>> writes, CachedOutput cache, String name, String parent) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:item/" + parent);
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "tge:item/" + name);
        json.add("textures", textures);
        writes.add(DataProvider.saveStable(cache, json, client("models/item/" + name + ".json")));
    }

    private void language(List<CompletableFuture<?>> writes, CachedOutput cache, String locale, JsonObject json) {
        writes.add(DataProvider.saveStable(cache, json, client("lang/" + locale + ".json")));
    }

    private void recipes(List<CompletableFuture<?>> writes, CachedOutput cache) {
        RecipeFile.shaped("tge:unfired_clay_brick", 8)
                .pattern("CCC", "CMC", "CCC")
                .define('C', "minecraft:clay_ball")
                .define('M', "tge:brick_mold")
                .save(writes, cache, server("tge", "recipe/crafting/unfired_clay_brick.json"));

        RecipeFile.smelting("minecraft:brick")
                .ingredient("tge:unfired_clay_brick")
                .experience(0.3F)
                .cookingTime(200)
                .save(writes, cache, server("minecraft", "recipe/brick.json"));

        toolRecipe(writes, cache, "iron_hammer")
                .pattern("III", "III", " S ")
                .defineTag('I', IRON_INGOT_TAG)
                .define('S', "minecraft:stick")
                .save(writes, cache, server("tge", "recipe/crafting/iron_hammer.json"));

        toolRecipe(writes, cache, "iron_file")
                .pattern("P", "P", "S")
                .defineTag('P', IRON_PLATE_TAG)
                .define('S', "minecraft:stick")
                .save(writes, cache, server("tge", "recipe/crafting/iron_file.json"));

        toolRecipe(writes, cache, "iron_wrench")
                .pattern("PHP", " P ", " I ")
                .defineTag('P', IRON_PLATE_TAG)
                .defineTag('H', HAMMER_TAG)
                .defineTag('I', IRON_INGOT_TAG)
                .save(writes, cache, server("tge", "recipe/crafting/iron_wrench.json"));

        toolRecipe(writes, cache, "iron_screwdriver")
                .pattern("  C", "FR ", "SH ")
                .defineTag('C', IRON_SCREW_TAG)
                .defineTag('F', FILE_TAG)
                .defineTag('R', IRON_ROD_TAG)
                .define('S', "minecraft:stick")
                .defineTag('H', HAMMER_TAG)
                .save(writes, cache, server("tge", "recipe/crafting/iron_screwdriver.json"));

        toolRecipe(writes, cache, "iron_mortar")
                .pattern(" I ", "GIG", "GGG")
                .defineTag('I', IRON_INGOT_TAG)
                .define('G', "minecraft:stone")
                .save(writes, cache, server("tge", "recipe/crafting/iron_mortar.json"));

        toolRecipe(writes, cache, "iron_saw")
                .pattern("PPS", "FHS")
                .defineTag('P', IRON_PLATE_TAG)
                .define('S', "minecraft:stick")
                .defineTag('F', FILE_TAG)
                .defineTag('H', HAMMER_TAG)
                .save(writes, cache, server("tge", "recipe/crafting/iron_saw.json"));

        materialRecipes(writes, cache);
    }

    private void materialRecipes(List<CompletableFuture<?>> writes, CachedOutput cache) {
        for (String material : ORE_AND_ALLOY_MATERIALS) {
            String tagMaterial = tagMaterial(material);

            RecipeFile.shaped("ore_and_alloy:" + material + "_plate", 1)
                    .pattern("H", "I", "I")
                    .defineTag('H', HAMMER_TAG)
                    .defineTag('I', "c:ingots/" + tagMaterial)
                    .save(writes, cache, server(
                            "tge", "recipe/crafting/materials/" + material + "_plate.json"));

            RecipeFile.shaped("ore_and_alloy:" + material + "_rod", 1)
                    .pattern("F ", " I")
                    .defineTag('F', FILE_TAG)
                    .defineTag('I', "c:ingots/" + tagMaterial)
                    .save(writes, cache, server(
                            "tge", "recipe/crafting/materials/" + material + "_rod.json"));
        }
    }

    private String tagMaterial(String material) {
        return "aluminium".equals(material) ? "aluminum" : material;
    }

    private void toolTags(List<CompletableFuture<?>> writes, CachedOutput cache) {
        tag(writes, cache, "pickaxes");
        tag(writes, cache, "axes");
        tag(writes, cache, "shovels");
        tag(writes, cache, "hoes");
        tag(writes, cache, "swords");
        tgeToolTag(writes, cache, "hammers", TGEToolType.HAMMER);
        tgeToolTag(writes, cache, "wrenches", TGEToolType.WRENCH);
        tgeToolTag(writes, cache, "saws", TGEToolType.SAW);
        tgeToolTag(writes, cache, "files", TGEToolType.FILE);
        tgeToolTag(writes, cache, "mortars", TGEToolType.MORTAR);
        tgeToolTag(writes, cache, "wire_cutters", TGEToolType.WIRE_CUTTERS);
        tgeToolTag(writes, cache, "screwdrivers", TGEToolType.SCREWDRIVER);
    }

    private void tag(List<CompletableFuture<?>> writes, CachedOutput cache, String name) {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        var values = new com.google.gson.JsonArray();
        for (TGETools.Entry tool : TGETools.entries()) {
            if (name.equals(tool.type().minecraftItemTag())) {
                values.add("tge:" + tool.id());
            }
        }
        json.add("values", values);
        writes.add(DataProvider.saveStable(cache, json, server("minecraft", "tags/item/" + name + ".json")));
    }

    private void tgeToolTag(List<CompletableFuture<?>> writes, CachedOutput cache, String name, TGEToolType type) {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        var values = new com.google.gson.JsonArray();
        for (TGETools.Entry tool : TGETools.entries()) {
            if (tool.type() == type) {
                values.add("tge:" + tool.id());
            }
        }
        json.add("values", values);
        writes.add(DataProvider.saveStable(cache, json, server("tge", "tags/item/tools/" + name + ".json")));
    }

    private JsonObject english() {
        JsonObject json = commonLanguage();
        json.addProperty("item.tge.glass_dust", "Glass Dust");
        json.addProperty("item.tge.flint_dust", "Flint Dust");
        json.addProperty("item.tge.clay_dust", "Clay Dust");
        json.addProperty("item.tge.brick_dust", "Brick Dust");
        json.addProperty("item.tge.fire_clay_dust", "Fire Clay Dust");
        json.addProperty("item.tge.fire_brick", "Fire Brick");
        json.addProperty("item.tge.unfired_clay_brick", "Unfired Clay Brick");
        json.addProperty("item.tge.brick_mold", "Brick Mold");
        json.addProperty("tooltip.tge.tool.max_durability", "Max durability: ");
        json.addProperty("tooltip.tge.tool.durability", "Durability: ");
        json.addProperty("tooltip.tge.tool.crafting_uses", "Crafting uses: ");
        json.addProperty("tooltip.tge.tool.tier", "Tier: ");
        for (TGETools.Entry tool : TGETools.entries()) {
            json.addProperty("item.tge." + tool.id(), tool.displayName());
        }
        return json;
    }

    private JsonObject commonLanguage() {
        JsonObject json = new JsonObject();
        json.addProperty("itemGroup.tge.main", "TGE Core");
        json.addProperty("itemGroup.tge.tools", "TGE Tools");
        return json;
    }

    private RecipeFile toolRecipe(List<CompletableFuture<?>> writes, CachedOutput cache, String item) {
        return RecipeFile.shaped("tge:" + item, 1);
    }

    private Path client(String relative) {
        return output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve("tge").resolve(relative);
    }

    private Path server(String namespace, String relative) {
        return output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(namespace).resolve(relative);
    }

    @Override
    public String getName() {
        return "TGE generated resources";
    }

    private static final class RecipeFile {
        private final JsonObject json = new JsonObject();

        private RecipeFile(String type, String category, String result, int count) {
            json.addProperty("type", type);
            json.addProperty("category", category);
            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("id", result);
            if (count > 1) {
                resultJson.addProperty("count", count);
            }
            json.add("result", resultJson);
        }

        static RecipeFile shaped(String result, int count) {
            return new RecipeFile("minecraft:crafting_shaped", "misc", result, count)
                    .withArray("pattern")
                    .withObject("key");
        }

        static RecipeFile smelting(String result) {
            return new RecipeFile("minecraft:smelting", "misc", result, 1);
        }

        RecipeFile pattern(String... rows) {
            var pattern = json.getAsJsonArray("pattern");
            for (String row : rows) {
                pattern.add(row);
            }
            return this;
        }

        RecipeFile define(char symbol, String item) {
            json.getAsJsonObject("key").add(String.valueOf(symbol), ingredientJson(item));
            return this;
        }

        RecipeFile defineTag(char symbol, String tag) {
            json.getAsJsonObject("key").add(String.valueOf(symbol), tagIngredientJson(tag));
            return this;
        }

        RecipeFile ingredient(String item) {
            json.add("ingredient", ingredientJson(item));
            return this;
        }

        RecipeFile experience(float experience) {
            json.addProperty("experience", experience);
            return this;
        }

        RecipeFile cookingTime(int ticks) {
            json.addProperty("cookingtime", ticks);
            return this;
        }

        void save(List<CompletableFuture<?>> writes, CachedOutput cache, Path path) {
            writes.add(DataProvider.saveStable(cache, json, path));
        }

        private RecipeFile withArray(String name) {
            json.add(name, new com.google.gson.JsonArray());
            return this;
        }

        private RecipeFile withObject(String name) {
            json.add(name, new JsonObject());
            return this;
        }

        private static JsonObject ingredientJson(String item) {
            JsonObject ingredient = new JsonObject();
            ingredient.addProperty("item", item);
            return ingredient;
        }

        private static JsonObject tagIngredientJson(String tag) {
            JsonObject ingredient = new JsonObject();
            ingredient.addProperty("tag", tag);
            return ingredient;
        }
    }
}
