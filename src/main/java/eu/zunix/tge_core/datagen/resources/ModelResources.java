package eu.zunix.tge_core.datagen.resources;

import com.google.gson.JsonObject;
import eu.zunix.tge_core.datagen.helper.DataPaths;
import eu.zunix.tge_core.datagen.helper.GeneratedData;
import eu.zunix.tge_core.registry.Tools;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

public final class ModelResources {
    private ModelResources() {
    }

    public static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        for (String item : GeneratedData.ITEMS) {
            model(output, writes, cache, item, "generated");
        }
        for (Tools.Entry tool : Tools.entries()) {
            model(output, writes, cache, tool.id(), "handheld");
        }
    }

    private static void model(
            PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache, String name, String parent) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "minecraft:item/" + parent);
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "tge:item/" + name);
        json.add("textures", textures);
        writes.add(DataProvider.saveStable(cache, json, DataPaths.client(output, "models/item/" + name + ".json")));
    }
}
