package eu.zunix.tge_core.datagen.recipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;

public final class RecipeResources {
    private RecipeResources() {
    }

    public static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        CoreRecipeResources.generate(output, writes, cache);
        MinecraftRecipeResources.generate(output, writes, cache);
    }
}
