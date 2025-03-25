package net.zeus_32.tge_core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zeus_32.tge_core.common.ModItems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
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

    private static final List<String> MANUAL_TOOLS_MATERIALS = Arrays.asList(
            "bronze"
    );

    private static final List<String> MANUAL_TOOLS_TYPES = Arrays.asList(
            "hammer"
    );

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        for (String material : MATERIALS) {
            if (!material.equals("plutonium") && !material.equals("polonium") && !material.equals("uranium")) {
                registerIngotFromNuggetsRecipe(recipeOutput, material);
                registerNuggetsFromIngotRecipe(recipeOutput, material);
            }
        }

        registerCopperNuggetsFromIngotRecipe(recipeOutput);
        registerCopperIngotFromNuggetsRecipe(recipeOutput);

// FINISH RECIPES
    // MISSING:
        // FILE, HAMMER, MORTAR, SAW, SCREWDRIVER, WIRE_CUTTERS, WRENCH
        // PLATE, ROD, GEAR, BOLT, SCREW
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.ALUMINIUM_PLATE)
                .unlockedBy("has_ingot", has(ModItems.ALUMINIUM_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/aluminum_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRONZE_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.BRONZE_PLATE)
                .unlockedBy("has_ingot", has(ModItems.BRONZE_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/bronze_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', Items.DIAMOND)
                .unlockedBy("has_ingot", has(Items.DIAMOND))
                .save(recipeOutput, "tge_core:crafting/tool/diamond_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INVAR_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.INVAR_PLATE)
                .unlockedBy("has_ingot", has(ModItems.INVAR_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/invar_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.IRON_PLATE)
                .unlockedBy("has_ingot", has(ModItems.IRON_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/iron_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_STEEL_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.STAINLESS_STEEL_PLATE)
                .unlockedBy("has_ingot", has(ModItems.STAINLESS_STEEL_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/stainless_steel_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.STEEL_PLATE)
                .unlockedBy("has_ingot", has(ModItems.STEEL_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/steel_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.TITANIUM_PLATE)
                .unlockedBy("has_ingot", has(ModItems.TITANIUM_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/titanium_file");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WROUGHT_IRON_FILE.get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.WROUGHT_IRON_PLATE)
                .unlockedBy("has_ingot", has(ModItems.WROUGHT_IRON_PLATE.get()))
                .save(recipeOutput, "tge_core:crafting/tool/wrought_iron_file");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALUMINIUM_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.ALUMINIUM_INGOT)
                .unlockedBy("has_ingot", has(ModItems.ALUMINIUM_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/aluminum_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRONZE_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.BRONZE_INGOT)
                .unlockedBy("has_ingot", has(ModItems.BRONZE_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/bronze_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', Items.DIAMOND)
                .unlockedBy("has_ingot", has(Items.DIAMOND))
                .save(recipeOutput, "tge_core:crafting/tool/diamond_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INVAR_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.INVAR_INGOT)
                .unlockedBy("has_ingot", has(ModItems.INVAR_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/invar_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput, "tge_core:crafting/tool/iron_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAINLESS_STEEL_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.STAINLESS_STEEL_INGOT)
                .unlockedBy("has_ingot", has(ModItems.STAINLESS_STEEL_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/stainless_steel_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.STEEL_INGOT)
                .unlockedBy("has_ingot", has(ModItems.STEEL_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/steel_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TITANIUM_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.TITANIUM_INGOT)
                .unlockedBy("has_ingot", has(ModItems.TITANIUM_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/titanium_hammer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WROUGHT_IRON_HAMMER.get())
                .pattern("III")
                .pattern("III")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('I', ModItems.WROUGHT_IRON_INGOT)
                .unlockedBy("has_ingot", has(ModItems.WROUGHT_IRON_INGOT.get()))
                .save(recipeOutput, "tge_core:crafting/tool/wrought_iron_hammer");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRONZE_PLATE.get())
                .pattern(" H ")
                .pattern(" I ")
                .pattern(" I ")
                .define('H', ModItems.BRONZE_HAMMER.get())
                .define('I', ModItems.BRONZE_INGOT)
                .unlockedBy("has_hammer", has(ModItems.BRONZE_HAMMER.get()))
                .save(recipeOutput, "tge_core:crafting/metal/plate/bronze_plate");
    }


    private void registerIngotFromNuggetsRecipe(RecipeOutput recipeOutput, String material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_ingot"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Ingot not found: " + material + "_ingot"))
                        .get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Nugget not found: " + material + "_nugget"))
                        .get())
                .unlockedBy("has_" + material + "_nugget", has(ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Nugget not found: " + material + "_nugget"))
                        .get()))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/" + material + "_ingot_from_nuggets");
    }

    private void registerNuggetsFromIngotRecipe(RecipeOutput recipeOutput, String material) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Nugget not found: " + material + "_nugget"))
                        .get(), 9)
                .requires(ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_ingot"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Ingot not found: " + material + "_ingot"))
                        .get())
                .unlockedBy("has_" + material + "_ingot", has(ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals(material + "_ingot"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Ingot not found: " + material + "_ingot"))
                        .get()))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/" + material + "_nuggets_from_ingot");
    }

    private void registerCopperNuggetsFromIngotRecipe(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, (ItemLike) ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals("copper_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Copper nugget not found")), 9)
                .requires(Items.COPPER_INGOT) // Použití klasického Minecraft copper ingotu
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/copper_nuggets_from_ingot");
    }

    private void registerCopperIngotFromNuggetsRecipe(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COPPER_INGOT) // Výsledek je klasický Minecraft copper ingot
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', (ItemLike) ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals("copper_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Copper nugget not found")))
                .unlockedBy("has_copper_nugget", has((ItemLike) ModItems.ITEMS.getEntries().stream()
                        .filter(entry -> entry.getId().getPath().equals("copper_nugget"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Copper nugget not found"))))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/copper_ingot_from_nuggets");
    }
}