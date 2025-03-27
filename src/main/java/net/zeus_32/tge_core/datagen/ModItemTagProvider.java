package net.zeus_32.tge_core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    private static final List<String> MATERIALS = Arrays.asList(
            "aluminium",
            "brass",
            "bronze",
            "cobalt",
            "copper",
            "cupronickel",
            "electrum",
            "enderium",
            "gold",
            "invar",
            "iridium",
            "iron",
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
            "wrench",
            "knife"
    );

    // Tool tags
    public static final TagKey<Item> HAMMERS = createTag("tge", "hammers");
    public static final TagKey<Item> FILES = createTag("tge", "files");
    public static final TagKey<Item> MORTARS = createTag("tge", "mortars");
    public static final TagKey<Item> SAWS = createTag("tge", "saws");
    public static final TagKey<Item> SCREWDRIVERS = createTag("tge", "screwdrivers");
    public static final TagKey<Item> WIRE_CUTTERS = createTag("tge", "wire_cutters");
    public static final TagKey<Item> WRENCHES = createTag("tge", "wrenches");
    public static final TagKey<Item> KNIVES = createTag("tge", "knives");

    // Common category tags
    private static final TagKey<Item> C_INGOTS = createTag("c", "ingots");
    private static final TagKey<Item> C_NUGGETS = createTag("c", "nuggets");
    private static final TagKey<Item> C_PLATES = createTag("c", "plates");
    private static final TagKey<Item> C_RODS = createTag("c", "rods");
    private static final TagKey<Item> C_GEARS = createTag("c", "gears");
    private static final TagKey<Item> C_BOLTS = createTag("c", "bolts");
    private static final TagKey<Item> C_SCREWS = createTag("c", "screws");
    private static final TagKey<Item> C_DUSTS = createTag("c", "dusts");

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TGECore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Add tags for all materials
        for (String material : MATERIALS) {
            // Create material-specific tags
            TagKey<Item> materialIngotTag = createTag("c", "ingots/" + material);
            TagKey<Item> materialNuggetTag = createTag("c", "nuggets/" + material);
            TagKey<Item> materialPlateTag = createTag("c", "plates/" + material);
            TagKey<Item> materialRodTag = createTag("c", "rods/" + material);
            TagKey<Item> materialGearTag = createTag("c", "gears/" + material);
            TagKey<Item> materialBoltTag = createTag("c", "bolts/" + material);
            TagKey<Item> materialScrewTag = createTag("c", "screws/" + material);
            TagKey<Item> materialDustTag = createTag("c", "dusts/" + material);

            // Handle ingots - add to both general and specific tags
            addItemToBothTags(material + "_ingot", C_INGOTS, materialIngotTag);

            // Handle nuggets - skip radioactive materials and vanilla nuggets
            if (!material.equals("plutonium") && !material.equals("polonium") && !material.equals("uranium")) {
                addItemToBothTags(material + "_nugget", C_NUGGETS, materialNuggetTag);
            }

            // Handle other components - skip radioactive materials
            if (!material.equals("plutonium") && !material.equals("polonium") && !material.equals("uranium")) {
                addItemToBothTags(material + "_plate", C_PLATES, materialPlateTag);
                addItemToBothTags(material + "_rod", C_RODS, materialRodTag);
                addItemToBothTags(material + "_gear", C_GEARS, materialGearTag);
                addItemToBothTags(material + "_bolt", C_BOLTS, materialBoltTag);
                addItemToBothTags(material + "_screw", C_SCREWS, materialScrewTag);
                addItemToBothTags(material + "_dust", C_DUSTS, materialDustTag);
            }
        }

        // Add vanilla items to common tags
        tag(C_INGOTS).add(Items.IRON_INGOT, Items.GOLD_INGOT, Items.COPPER_INGOT);
        tag(C_NUGGETS).add(Items.IRON_NUGGET, Items.GOLD_NUGGET);

        // Add tags for tools
        for (String material : MANUAL_TOOLS_MATERIALS) {
            addItemToTagIfExists(material + "_hammer", HAMMERS);
            addItemToTagIfExists(material + "_file", FILES);
            addItemToTagIfExists(material + "_mortar", MORTARS);
            addItemToTagIfExists(material + "_saw", SAWS);
            addItemToTagIfExists(material + "_screwdriver", SCREWDRIVERS);
            addItemToTagIfExists(material + "_wire_cutters", WIRE_CUTTERS);
            addItemToTagIfExists(material + "_wrench", WRENCHES);
            addItemToTagIfExists(material + "_knife", KNIVES);
        }
    }

    private void addItemToBothTags(String itemName, TagKey<Item> generalTag, TagKey<Item> specificTag) {
        ModItems.ITEMS.getEntries().stream()
                .filter(entry -> entry.getId().getPath().equals(itemName))
                .findFirst()
                .ifPresent(entry -> {
                    tag(generalTag).add(entry.get());
                    tag(specificTag).add(entry.get());
                });
    }

    private void addItemToTagIfExists(String itemName, TagKey<Item> tag) {
        ModItems.ITEMS.getEntries().stream()
                .filter(entry -> entry.getId().getPath().equals(itemName))
                .findFirst()
                .ifPresent(entry -> tag(tag).add(entry.get()));
    }

    private static TagKey<Item> createTag(String namespace, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}