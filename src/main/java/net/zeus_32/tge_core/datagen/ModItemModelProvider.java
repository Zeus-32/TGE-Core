package net.zeus_32.tge_core.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeus_32.tge_core.TGECore;

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

        registerNonMetalItems("clay", "dust");
        registerNonMetalItems("brick", "dust");
        registerNonMetalItems("flint", "dust");
        registerNonMetalItems("quartz_sand", "dust");
        registerNonMetalItems("glass", "dust");
        registerNonMetalItems("brick", "mold");
        registerNonMetalItems("wet", "coke_brick");
        registerNonMetalItems("coke", "brick");
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
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_plate";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/plate/" + itemName));
    }
    private void registerNuggetModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium") || material.equals("gold") || material.equals("iron")) return;
        String itemName = material + "_nugget";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/nugget/" + itemName));
    }
    private void registerRodModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_rod";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/rod/" + itemName));
    }
    private void registerGearModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_gear";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/gear/" + itemName));
    }
    private void registerBoltModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_bolt";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/bolt/" + itemName));
    }
    private void registerScrewModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_screw";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/screw/" + itemName));
    }
    private void registerDustModel(String material) {
        if (material.equals("plutonium") || material.equals("polonium") || material.equals("uranium")) return;
        String itemName = material + "_dust";
        withExistingParent(itemName, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/common/item/dust/" + itemName));
    }

    private void registerTool(String material, String type) {
        if (type != "knife") {
            String itemName = material + "_" + type;
            withExistingParent(itemName, mcLoc("item/generated"))
                    .texture("layer0", modLoc("item/common/tools/manual_tools/" + material + "/" + itemName));
        } else {
            withExistingParent(material + "_knife", mcLoc("item/handheld"))
                    .texture("layer0", modLoc("item/common/tools/manual_tools/knife/" + material + "_knife"));
        }
    }
}