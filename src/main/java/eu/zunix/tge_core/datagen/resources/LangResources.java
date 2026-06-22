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

        json.addProperty("tooltip.tge.tool.max_durability", "Max durability");
        json.addProperty("tooltip.tge.tool.durability", "Durability");
        json.addProperty("tooltip.tge.tool.crafting_uses", "Crafting uses");
        json.addProperty("tooltip.tge.tool.tier", "Tier");
        json.addProperty("tooltip.tge.ability.lumberjack", "Lumberjack");
        json.addProperty(
                "tooltip.tge.ability.lumberjack.description",
                "Chops connected logs");
        json.addProperty("tooltip.tge.ability.torchbearer", "Torchbearer");
        json.addProperty(
                "tooltip.tge.ability.torchbearer.description",
                "Places torches from inventory");
        json.addProperty("tooltip.tge.ability.farmer", "Farmer");
        json.addProperty(
                "tooltip.tge.ability.farmer.description",
                "Tills a 3x3 area");
        json.addProperty("tooltip.tge.ability.duelist", "Duelist");
        json.addProperty(
                "tooltip.tge.ability.duelist.description",
                "+2 entity attack range");
        json.addProperty("tooltip.tge.ability.excavator", "Excavator");
        json.addProperty(
                "tooltip.tge.ability.excavator.description",
                "Mines a 3x3 layer");
        json.addProperty("tag.item.tge.tools", "Tools");
        for (GeneratedData.ToolTag tag : GeneratedData.TOOL_TAGS) {
            json.addProperty("tag.item.tge.tools." + tag.name(), title(tag.name()));
        }
        for (String tag : GeneratedData.MINECRAFT_TOOL_TAGS) {
            json.addProperty("tag.item.minecraft." + tag, title(tag));
        }
        for (Tools.Entry tool : Tools.entries()) {
            json.addProperty("item.tge." + tool.id(), tool.displayName());
        }
        for (Blocks.Entry block : Blocks.entries()) {
            json.addProperty("block.tge." + block.id(), title(block.id()));
        }
        writes.add(DataProvider.saveStable(cache, json, DataPaths.client(output, "lang/en_us.json")));
    }

    private static String title(String id) {
        StringBuilder title = new StringBuilder();
        for (String word : id.split("_")) {
            if (!title.isEmpty()) {
                title.append(' ');
            }
            title.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return title.toString();
    }
}
