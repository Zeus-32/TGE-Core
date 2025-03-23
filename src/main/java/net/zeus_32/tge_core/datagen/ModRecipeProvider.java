package net.zeus_32.tge_core.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
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