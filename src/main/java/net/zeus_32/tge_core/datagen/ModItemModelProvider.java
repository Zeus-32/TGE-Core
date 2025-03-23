package net.zeus_32.tge_core.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeus_32.tge_core.TGECore;

import java.util.Arrays;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    // Seznam materiálů
    private static final List<String> MATERIALS = Arrays.asList(
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

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TGECore.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (String material : MATERIALS) {
            registerIngotModel(material);
            registerPlateModel(material);
            registerNuggetModel(material);
        }
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
}