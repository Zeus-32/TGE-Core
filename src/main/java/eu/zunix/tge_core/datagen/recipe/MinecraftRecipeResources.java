package eu.zunix.tge_core.datagen.recipe;

import eu.zunix.tge_core.datagen.helper.DataPaths;
import eu.zunix.tge_core.datagen.helper.GeneratedData;
import eu.zunix.tge_core.datagen.helper.RecipeFile;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;

final class MinecraftRecipeResources {
    private MinecraftRecipeResources() {
    }

    static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        RecipeFile.smelting("minecraft:brick")
                .ingredient("tge:unfired_clay_brick")
                .experience(0.3F)
                .cookingTime(200)
                .save(writes, cache, DataPaths.server(output, "minecraft", "recipe/brick.json"));

        RecipeFile.smelting("minecraft:glass")
                .ingredient("tge:glass_dust")
                .experience(0.1F)
                .cookingTime(200)
                .save(writes, cache, DataPaths.server(output, "minecraft", "recipe/glass.json"));

        RecipeFile.shaped("minecraft:bucket", 1)
                .pattern("PHP", " P ")
                .defineTag('P', "c:plates/iron")
                .defineTag('H', GeneratedData.HAMMER_TAG)
                .save(writes, cache, DataPaths.server(output, "minecraft", "recipe/bucket.json"));
    }
}
