package eu.zunix.tge_core.datagen;

import eu.zunix.tge_core.datagen.recipe.RecipeResources;
import eu.zunix.tge_core.datagen.resources.LangResources;
import eu.zunix.tge_core.datagen.resources.ModelResources;
import eu.zunix.tge_core.datagen.resources.TagResources;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

public final class GeneratedResources implements DataProvider {
    private final PackOutput output;

    public GeneratedResources(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();
        ModelResources.generate(output, writes, cache);
        LangResources.generate(output, writes, cache);
        TagResources.generate(output, writes, cache);
        RecipeResources.generate(output, writes, cache);
        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "TGE generated resources";
    }
}
