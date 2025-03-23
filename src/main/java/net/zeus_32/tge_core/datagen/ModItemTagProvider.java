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
    // Seznam materiálů
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

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TGECore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (String material : MATERIALS) {
            TagKey<Item> tag_ingot = createTag("c", "ingots/" + material);
            TagKey<Item> tag_nugget = createTag("c", "nuggets/" + material);
            TagKey<Item> tag_plate = createTag("c", "plates/" + material);

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

            tag(tag_ingot).add(ingot);
        }
    }

    // Pomocná metoda pro vytvoření TagKey
    private TagKey<Item> createTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}