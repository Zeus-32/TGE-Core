package net.zeus_32.tge_core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.common.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    private static final List<String> MATERIALS = Arrays.asList(
            "brass",
            "bronze",
            "cobalt",
            "cupronickel",
            "electrum",
            "enderium",
            "invar",
            "iridium",
            "lead",
            "lumium",
            "naquadah",
            "nickel",
            "osmium",
            "platinum",
            "plutonium",
            "polonium",
            "red_alloy",
            "silver",
            "soul_infused",
            "stainless_steel",
            "steel",
            "tin",
            "titanium",
            "uranium",
            "wrought_iron",
            "zinc"
    );

    // CREATE TAGS FOR:
        // FILE, HAMMER, MORTAR, SAW, SCREWDRIVER, WIRE_CUTTERS, WRENCH
    private static final List<String> MANUAL_TOOLS_MATERIALS = Arrays.asList(
            "aluminium",
            "bronze",
            "diamond",
            "invar",
            "iron",
            "stainless_steel",
            "steel",
            "titanium",
            "wrought_iron"
    );

    private static final List<String> MANUAL_TOOLS_TYPES = Arrays.asList(
            "file",
            "hammer",
            "mortar",
            "saw",
            "screwdriver",
            "wire_cutters",
            "wrench"
    );

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TGECore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagKey<Item> tag_hammer = createTag("tge", "hammers");
        for (String material : MATERIALS) {
            TagKey<Item> tag_ingot = createTag("c", "ingots/" + material);
            TagKey<Item> tag_nugget = createTag("c", "nuggets/" + material);
            TagKey<Item> tag_plate = createTag("c", "plates/" + material);
            TagKey<Item> tag_rod = createTag("c", "rods/" + material);
            TagKey<Item> tag_gear = createTag("c", "gears/" + material);
            TagKey<Item> tag_bolt = createTag("c", "bolts/" + material);
            TagKey<Item> tag_screw = createTag("c", "screws/" + material);

            Item ingot = ModItems.ITEMS.getEntries().stream()
                    .filter(entry -> entry.getId().getPath().equals(material + "_ingot"))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Ingot not found: " + material + "_ingot"))
                    .get();

            if (material.equals("iron") || material.equals("gold") || material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping nugget for material: " + material);
            } else {
                Item nugget = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Nugget not found: " + material + "_nugget"))
                        .get();
                tag(tag_nugget).add(nugget);
            }

            if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping plate for material: " + material);
            } else {
                Item plate = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_plate"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Plate not found: " + material + "_plate"))
                        .get();
                tag(tag_plate).add(plate);
            }

            if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping Rod for material: " + material);
            } else {
                Item rod = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_rod"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Plate not found: " + material + "_rod"))
                        .get();
                tag(tag_rod).add(rod);
            }

            if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping plate for material: " + material);
            } else {
                Item gear = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_gear"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Plate not found: " + material + "_gear"))
                        .get();
                tag(tag_gear).add(gear);
            }

            if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping plate for material: " + material);
            } else {
                Item bolt = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_bolt"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Plate not found: " + material + "_bolt"))
                        .get();
                tag(tag_bolt).add(bolt);
            }

            if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) {
                System.out.println("Skipping plate for material: " + material);
            } else {
                Item screw = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_screw"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Plate not found: " + material + "_screw"))
                        .get();
                tag(tag_screw).add(screw);
            }

            tag(tag_ingot).add(ingot);
        }


        for (String material : MANUAL_TOOLS_MATERIALS) {
            for (String type : MANUAL_TOOLS_TYPES) {
                Item hammer = ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_" + type))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Hammer not found"))
                        .get();
                tag(tag_hammer).add(hammer);
            }
        }
    }

    private TagKey<Item> createTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}