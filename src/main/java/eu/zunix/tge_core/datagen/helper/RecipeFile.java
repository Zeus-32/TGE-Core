package eu.zunix.tge_core.datagen.helper;

import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

public final class RecipeFile {
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

    public static RecipeFile shapeless(String result, int count) {
        return new RecipeFile("minecraft:crafting_shapeless", "misc", result, count)
                .withArray("ingredients");
    }

    public static RecipeFile shaped(String result, int count) {
        return new RecipeFile("minecraft:crafting_shaped", "misc", result, count)
                .withArray("pattern")
                .withObject("key");
    }

    public static RecipeFile smelting(String result) {
        return new RecipeFile("minecraft:smelting", "misc", result, 1);
    }

    public RecipeFile pattern(String... rows) {
        var pattern = json.getAsJsonArray("pattern");
        for (String row : rows) {
            pattern.add(row);
        }
        return this;
    }

    public RecipeFile define(char symbol, String item) {
        json.getAsJsonObject("key").add(String.valueOf(symbol), ingredientJson(item));
        return this;
    }

    public RecipeFile defineTag(char symbol, String tag) {
        json.getAsJsonObject("key").add(String.valueOf(symbol), tagIngredientJson(tag));
        return this;
    }

    public RecipeFile itemExists(String item) {
        var conditions = new com.google.gson.JsonArray();
        JsonObject condition = new JsonObject();
        condition.addProperty("type", "neoforge:item_exists");
        condition.addProperty("item", item);
        conditions.add(condition);
        json.add("neoforge:conditions", conditions);
        return this;
    }

    public RecipeFile ingredient(String item) {
        if (json.has("ingredients")) {
            json.getAsJsonArray("ingredients").add(ingredientJson(item));
        } else {
            json.add("ingredient", ingredientJson(item));
        }
        return this;
    }

    public RecipeFile ingredientTag(String tag) {
        if (json.has("ingredients")) {
            json.getAsJsonArray("ingredients").add(tagIngredientJson(tag));
        } else {
            json.add("ingredient", tagIngredientJson(tag));
        }
        return this;
    }

    public RecipeFile ingredients(String material, int count) {
        for (int i = 0; i < count; i++) {
            ingredient(material);
        }
        return this;
    }

    public RecipeFile ingredientTags(String tag, int count) {
        for (int i = 0; i < count; i++) {
            ingredientTag(tag);
        }
        return this;
    }

    public RecipeFile experience(float experience) {
        json.addProperty("experience", experience);
        return this;
    }

    public RecipeFile cookingTime(int ticks) {
        json.addProperty("cookingtime", ticks);
        return this;
    }

    public void save(List<CompletableFuture<?>> writes, CachedOutput cache, Path path) {
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
