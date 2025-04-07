package net.zeus_32.tge_core.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;

import java.util.Arrays;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
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
            "red_alloy",
            "silver",
            "soul_infused",
            "stainless_steel",
            "steel",
            "tin",
            "titanium",
            "wrought_iron",
            "zinc"
    );

    private static final List<String> RAW_MATERIALS = Arrays.asList(
            "aluminium",
            "lead",
            "nickel",
            "osmium",
            "silver",
            "tin",
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
            "knife",
            "mining_hammer"
    );

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TGECore.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (String material : MATERIALS) {
            registerIngotModel(material);
            registerPlateModel(material);
            registerNuggetModel(material);
            registerRodModel(material);
            registerGearModel(material);
            registerBoltModel(material);
            registerScrewModel(material);
            registerDustModel(material);
        }

        for (String material : MANUAL_TOOLS_MATERIALS) {
            for (String type : MANUAL_TOOLS_TYPES) {
                registerTool(material, type);
            }
        }
        for (String material : RAW_MATERIALS) {
            registerRawModel(material);
        }

        registerNonMetalItems("clay", "dust");
        registerNonMetalItems("brick", "dust");
        registerNonMetalItems("flint", "dust");
        registerNonMetalItems("quartz_sand", "dust");
        registerNonMetalItems("glass", "dust");
        registerNonMetalItems("brick", "mold");
        registerNonMetalItems("wet", "coke_brick");
        registerNonMetalItems("coke", "brick");
        registerNonMetalItems("fire", "brick");
        registerNonMetalItems("andesite", "compound");
        registerNonMetalItems("advanced", "tool_handle");
        registerNonMetalItems("kinetic", "mechanism");
        registerNonMetalItems("wooden", "gear");
        registerNonMetalItems("water_wheel", "paddle");
        registerNonMetalItems("plant", "goo");
        registerNonMetalItems("drill", "head");
        registerNonMetalItems("saw", "blade");
        registerNonMetalItems("rubber", "sapling");
        registerNonMetalItems("sticky", "resin");


        saplingItem(ModBlocks.RUBBER_SAPLING);
    }
    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/" + item.getId().getPath()));
    }

    private void registerNonMetalItems(String material, String type) {
        String itemName = material + "_" + type;
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/non_metal/" + itemName));
    }

    private void registerIngotModel(String material) {
        if (material.equals("copper") || material.equals("gold") || material.equals("iron")) return;
        String itemName = material + "_ingot";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/ingot/" + itemName));
    }
    private void registerPlateModel(String material) {
        String itemName = material + "_plate";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/plate/" + itemName));
    }
    private void registerNuggetModel(String material) {
        if (material.equals("gold") || material.equals("iron")) return;
        String itemName = material + "_nugget";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/nugget/" + itemName));
    }
    private void registerRodModel(String material) {
        String itemName = material + "_rod";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/rod/" + itemName));
    }
    private void registerGearModel(String material) {
        String itemName = material + "_gear";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/gear/" + itemName));
    }
    private void registerBoltModel(String material) {
        String itemName = material + "_bolt";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/bolt/" + itemName));
    }
    private void registerScrewModel(String material) {
        String itemName = material + "_screw";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/screw/" + itemName));
    }
    private void registerDustModel(String material) {
        String itemName = material + "_dust";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/dust/" + itemName));
    }
    private void registerRawModel(String material) {
        String itemName = "raw_" + material;
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/raw_materials/" + itemName));
    }

    private void registerTool(String material, String type) {
        if (type != "knife") {
            String itemName = material + "_" + type;
            withExistingParent(itemName, mcLoc("item/handheld"))
                    .texture("layer0", modLoc("item/common/tools/manual_tools/" + material + "/" + itemName));
        } else {
            withExistingParent(material + "_knife", mcLoc("item/handheld"))
                    .texture("layer0", modLoc("item/common/tools/manual_tools/knife/" + material + "_knife"));
        }
    }
}