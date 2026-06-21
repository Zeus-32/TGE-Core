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

public final class LangResources {
    private LangResources() {
    }

    public static void generate(PackOutput output, List<CompletableFuture<?>> writes, CachedOutput cache) {
        JsonObject json = new JsonObject();
        json.addProperty("itemGroup.tge.main", "The Great Engineer");
        json.addProperty("itemGroup.tge.tools", "TGE Tools");
        json.addProperty("item.tge.glass_dust", "Glass Dust");
        json.addProperty("item.tge.quartz_dust", "Quartz Dust");
        json.addProperty("item.tge.flint_dust", "Flint Dust");
        json.addProperty("item.tge.clay_dust", "Clay Dust");
        json.addProperty("item.tge.brick_dust", "Brick Dust");
        json.addProperty("item.tge.fire_clay_dust", "Fire Clay Dust");
        json.addProperty("item.tge.compressed_fire_brick", "Compressed Fire Brick");
        json.addProperty("item.tge.fire_brick", "Fire Brick");
        json.addProperty("item.tge.unfired_clay_brick", "Unfired Clay Brick");
        json.addProperty("item.tge.brick_mold", "Brick Mold");
        json.addProperty("tooltip.tge.tool.max_durability", "Max durability: ");
        json.addProperty("tooltip.tge.tool.durability", "Durability: ");
        json.addProperty("tooltip.tge.tool.crafting_uses", "Crafting uses: ");
        json.addProperty("tooltip.tge.tool.tier", "Tier: ");
        for (Tools.Entry tool : Tools.entries()) {
            json.addProperty("item.tge." + tool.id(), tool.displayName());
        }
        writes.add(DataProvider.saveStable(cache, json, DataPaths.client(output, "lang/en_us.json")));
    }
}
