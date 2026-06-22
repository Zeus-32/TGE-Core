package eu.zunix.tge_core.datagen.resources;

import com.google.gson.JsonObject;
import eu.zunix.tge_core.datagen.helper.DataPaths;
import eu.zunix.tge_core.datagen.helper.GeneratedData;
import eu.zunix.tge_core.registry.Blocks;
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
        for (Blocks.Entry block : Blocks.entries()) {
            block(output, writes, cache, block.id());
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

    private static void block(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache, String name) {
        JsonObject blockState = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject normal = new JsonObject();
        normal.addProperty("model", "tge:block/" + name);
        variants.add("", normal);
        blockState.add("variants", variants);
        writes.add(DataProvider.saveStable(cache, blockState, DataPaths.client(output, "blockstates/" + name + ".json")));

        JsonObject blockModel = new JsonObject();
        blockModel.addProperty("parent", "minecraft:block/cube_all");
        JsonObject blockTextures = new JsonObject();
        blockTextures.addProperty("all", "tge:block/" + name);
        blockModel.add("textures", blockTextures);
        writes.add(DataProvider.saveStable(cache, blockModel, DataPaths.client(output, "models/block/" + name + ".json")));

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", "tge:block/" + name);
        writes.add(DataProvider.saveStable(cache, itemModel, DataPaths.client(output, "models/item/" + name + ".json")));
    }
}
