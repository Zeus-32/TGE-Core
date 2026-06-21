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

public final class TagResources {
    private TagResources() {
    }

    public static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        for (String tag : GeneratedData.MINECRAFT_TOOL_TAGS) {
            minecraftToolTag(output, writes, cache, tag);
        }
        for (GeneratedData.ToolTag tag : GeneratedData.TOOL_TAGS) {
            toolTag(output, writes, cache, tag);
        }
    }

    private static void minecraftToolTag(
            PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache, String name) {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        var values = new com.google.gson.JsonArray();
        for (Tools.Entry tool : Tools.entries()) {
            if (name.equals(tool.type().minecraftItemTag())) {
                values.add("tge:" + tool.id());
            }
        }
        json.add("values", values);
        writes.add(DataProvider.saveStable(
                cache, json, DataPaths.server(output, "minecraft", "tags/item/" + name + ".json")));
    }

    private static void toolTag(
            PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache, GeneratedData.ToolTag tag) {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        var values = new com.google.gson.JsonArray();
        for (Tools.Entry tool : Tools.entries()) {
            if (tool.type() == tag.type()) {
                values.add("tge:" + tool.id());
            }
        }
        json.add("values", values);
        writes.add(DataProvider.saveStable(
                cache, json, DataPaths.server(output, "tge", "tags/item/tools/" + tag.name() + ".json")));
    }
}
