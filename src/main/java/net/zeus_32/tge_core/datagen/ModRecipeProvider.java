package net.zeus_32.tge_core.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.item.ModItems;
import net.zeus_32.tge_core.item.ModNonMetalItems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
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

    private static final List<String> TOOL_MATERIALS = Arrays.asList(
            "aluminium",
            "bronze",
            "invar",
            "iron",
            "stainless_steel",
            "steel",
            "titanium",
            "wrought_iron"
    );

    private static final List<String> TOOL_TYPES = Arrays.asList(
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

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Register recipes for all materials
        for (String material : MATERIALS) {
            if (!material.equals("plutonium") && !material.equals("polonium") && !material.equals("uranium")) {
                // Skip vanilla nugget recipes as they exist in vanilla
                if (!List.of("iron", "gold", "copper").contains(material)) {
                    registerIngotFromNuggetsRecipe(recipeOutput, material);
                    registerNuggetsFromIngotRecipe(recipeOutput, material);
                }

                // Register component recipes for all materials
                registerPlateCrafting(recipeOutput, material);
                registerRodCrafting(recipeOutput, material);
                registerGearCrafting(recipeOutput, material);
                registerBoltCrafting(recipeOutput, material);
                registerScrewCrafting(recipeOutput, material);
            }
        }
        // Special copper nugget handling
        registerCopperNuggetsFromIngotRecipe(recipeOutput);
        registerCopperIngotFromNuggetsRecipe(recipeOutput);

        // Register tool recipes
        for (String material : TOOL_MATERIALS) {
            for (String toolType : TOOL_TYPES) {
                registerToolsRecipe(recipeOutput, material, toolType);
            }
        }

        registerShapedRecipes(recipeOutput);

        registerGlassChain(recipeOutput);
        registerCokeBrickChain(recipeOutput);
        registerFireBrickChain(recipeOutput);

        registerEditedCreateRecipes(recipeOutput);
    }

    private Item getItem(String itemName) {
        // First try to get from mod items
        try {
            return ModItems.ITEMS.getEntries().stream()
                    .filter(entry -> entry.getId().getPath().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Item not found in mod: " + itemName))
                    .get();
        } catch (IllegalStateException e) {
            // Fall back to vanilla items
            switch (itemName) {
                case "iron_ingot": return Items.IRON_INGOT;
                case "iron_nugget": return Items.IRON_NUGGET;
                case "gold_ingot": return Items.GOLD_INGOT;
                case "gold_nugget": return Items.GOLD_NUGGET;
                case "copper_ingot": return Items.COPPER_INGOT;
                case "iron_plate": return ModItems.IRON_PLATE.get();
                case "iron_rod": return ModItems.IRON_ROD.get();
                case "iron_gear": return ModItems.IRON_GEAR.get();
                case "iron_bolt": return ModItems.IRON_BOLT.get();
                case "iron_screw": return ModItems.IRON_SCREW.get();
                case "gold_plate": return ModItems.GOLD_PLATE.get();
                case "gold_rod": return ModItems.GOLD_ROD.get();
                case "gold_gear": return ModItems.GOLD_GEAR.get();
                case "gold_bolt": return ModItems.GOLD_BOLT.get();
                case "gold_screw": return ModItems.GOLD_SCREW.get();
                case "copper_plate": return ModItems.COPPER_PLATE.get();
                case "copper_rod": return ModItems.COPPER_ROD.get();
                case "copper_gear": return ModItems.COPPER_GEAR.get();
                case "copper_bolt": return ModItems.COPPER_BOLT.get();
                case "copper_screw": return ModItems.COPPER_SCREW.get();
                case "quartz_sand_dust": return ModNonMetalItems.QUARTZ_SAND_DUST.get();
                case "flint_dust": return ModNonMetalItems.FLINT_DUST.get();
                case "glass_dust": return ModNonMetalItems.GLASS_DUST.get();
                case "brick_mold": return ModNonMetalItems.BRICK_MOLD.get();
                case "wet_coke_brick": return ModNonMetalItems.WET_COKE_BRICK.get();
                case "coke_brick": return ModNonMetalItems.COKE_BRICK.get();
                case "clay_dust": return ModNonMetalItems.CLAY_DUST.get();
                case "brick_dust": return ModNonMetalItems.BRICK_DUST.get();
                case "fire_brick": return ModNonMetalItems.FIRE_BRICK.get();
                case "advanced_tool_handle": return ModItems.ADVANCED_TOOL_HANDLE.get();
                default: throw new IllegalStateException("Item not found: " + itemName);
            }
        }
    }

    private void registerShapedRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCED_TOOL_HANDLE)
                .pattern(" LR")
                .pattern("LRL")
                .pattern("RL ")
                .define('L', Items.LEATHER)
                .define('R', ModItems.STEEL_ROD)
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/tool/advanced_tool_handle");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_MACHINE_CASING)
                .pattern("IPI")
                .pattern("PCP")
                .pattern("IPI")
                .define('I', ModItems.STEEL_INGOT)
                .define('P', ModItems.STEEL_PLATE)
                .define('C', AllBlocks.ANDESITE_CASING.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/steel_machine_casing");
    }

    private void registerEditedCreateRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MECHANICAL_MIXER)
                .pattern(" S ")
                .pattern("MCM")
                .pattern(" I ")
                .define('S', AllBlocks.COGWHEEL)
                .define('C', AllBlocks.ANDESITE_CASING)
                .define('I', AllItems.WHISK)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM.get())
                .unlockedBy("has_casing", has(AllBlocks.ANDESITE_CASING))
                .unlockedBy("has_mechanism", has(ModNonMetalItems.KINTETIC_MECHANISM.get()))
                .save(recipeOutput, "create:crafting/kinetics/mechanical_mixer");
    }

    private void registerPlateCrafting(RecipeOutput recipeOutput, String material) {
        Item plate = getItem(material + "_plate");
        Item ingot = getItem(material + "_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plate)
                .pattern(" H ")
                .pattern(" I ")
                .pattern(" I ")
                .define('H', ModItemTagProvider.HAMMERS)
                .define('I', ingot)
                .unlockedBy("has_" + material + "_ingot", has(ingot))
                .save(recipeOutput, "tge_core:crafting/metal/plate/" + material + "_plate");
    }

    private void registerRodCrafting(RecipeOutput recipeOutput, String material) {
        Item rod = getItem(material + "_rod");
        Item ingot = getItem(material + "_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod, 2)
                .pattern("F  ")
                .pattern(" I ")
                .pattern("   ")
                .define('F', ModItemTagProvider.FILES)
                .define('I', ingot)
                .unlockedBy("has_" + material + "_ingot", has(ingot))
                .save(recipeOutput, "tge_core:crafting/metal/rod/" + material + "_rod");
    }

    private void registerGearCrafting(RecipeOutput recipeOutput, String material) {
        Item gear = getItem(material + "_gear");
        Item plate = getItem(material + "_plate");
        Item rod = getItem(material + "_rod");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, gear)
                .pattern("RPR")
                .pattern("PWP")
                .pattern("RPR")
                .define('R', rod)
                .define('P', plate)
                .define('W', ModItemTagProvider.WRENCHES)
                .unlockedBy("has_" + material + "_plate", has(plate))
                .save(recipeOutput, "tge_core:crafting/metal/gear/" + material + "_gear");
    }

    private void registerBoltCrafting(RecipeOutput recipeOutput, String material) {
        Item bolt = getItem(material + "_bolt");
        Item rod = getItem(material + "_rod");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, bolt, 2)
                .pattern("S  ")
                .pattern(" R ")
                .pattern("   ")
                .define('R', rod)
                .define('S', ModItemTagProvider.SAWS)
                .unlockedBy("has_" + material + "_rod", has(rod))
                .save(recipeOutput, "tge_core:crafting/metal/bolt/" + material + "_bolt");
    }

    private void registerScrewCrafting(RecipeOutput recipeOutput, String material) {
        Item screw = getItem(material + "_screw");
        Item bolt = getItem(material + "_bolt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, screw)
                .pattern("HB ")
                .pattern("B  ")
                .pattern("   ")
                .define('B', bolt)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_" + material + "_bolt", has(bolt))
                .save(recipeOutput, "tge_core:crafting/metal/screw/" + material + "_screw");
    }

    private void registerIngotFromNuggetsRecipe(RecipeOutput recipeOutput, String material) {
        Item ingot = getItem(material + "_ingot");
        Item nugget = getItem(material + "_nugget");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', nugget)
                .unlockedBy("has_" + material + "_nugget", has(nugget))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/" + material + "_ingot_from_nuggets");
    }

    private void registerNuggetsFromIngotRecipe(RecipeOutput recipeOutput, String material) {
        Item nugget = getItem(material + "_nugget");
        Item ingot = getItem(material + "_ingot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget, 9)
                .requires(ingot)
                .unlockedBy("has_" + material + "_ingot", has(ingot))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/" + material + "_nuggets_from_ingot");
    }

    private void registerCopperNuggetsFromIngotRecipe(RecipeOutput recipeOutput) {
        Item copperNugget = getItem("copper_nugget");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, copperNugget, 9)
                .requires(Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/copper_nuggets_from_ingot");
    }

    private void registerCopperIngotFromNuggetsRecipe(RecipeOutput recipeOutput) {
        Item copperNugget = getItem("copper_nugget");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COPPER_INGOT)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', copperNugget)
                .unlockedBy("has_copper_nugget", has(copperNugget))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/copper_ingot_from_nuggets");
    }

    private void registerToolsRecipe(RecipeOutput recipeOutput, String material, String toolType) {
        Item tool = getItem(material + "_" + toolType);

        switch (toolType) {
            case "file":
                Item plate = getItem(material + "_plate");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern(" I ")
                        .pattern(" I ")
                        .pattern(" S ")
                        .define('S', Items.STICK)
                        .define('I', plate)
                        .unlockedBy("has_" + material + "_plate", has(plate))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "hammer":
                Item ingot = getItem(material + "_ingot");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("III")
                        .pattern("III")
                        .pattern(" S ")
                        .define('S', Items.STICK)
                        .define('I', ingot)
                        .unlockedBy("has_" + material + "_ingot", has(ingot))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "mortar":
                Item mortarIngot = getItem(material + "_ingot");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern(" I ")
                        .pattern("SIS")
                        .pattern("SSS")
                        .define('S', Items.STONE)
                        .define('I', mortarIngot)
                        .unlockedBy("has_" + material + "_ingot", has(mortarIngot))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "saw":
                Item sawPlate = getItem(material + "_plate");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("SSS")
                        .pattern("IIP")
                        .pattern("HF ")
                        .define('S', Items.STICK)
                        .define('P', ItemTags.PLANKS)
                        .define('I', sawPlate)
                        .define('H', ModItemTagProvider.HAMMERS)
                        .define('F', ModItemTagProvider.FILES)
                        .unlockedBy("has_" + material + "_plate", has(sawPlate))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "screwdriver":
                Item screwdriverRod = getItem(material + "_rod");
                Item screwdriverBolt = getItem(material + "_bolt");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("  B")
                        .pattern("FR ")
                        .pattern("SH ")
                        .define('S', Items.STICK)
                        .define('R', screwdriverRod)
                        .define('B', screwdriverBolt)
                        .define('H', ModItemTagProvider.HAMMERS)
                        .define('F', ModItemTagProvider.FILES)
                        .unlockedBy("has_" + material + "_rod", has(screwdriverRod))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "wire_cutters":
                Item wireCuttersPlate = getItem(material + "_plate");
                Item wireCuttersScrew = getItem(material + "_screw");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("PBP")
                        .pattern("FPC")
                        .pattern("S S")
                        .define('S', Items.STICK)
                        .define('P', wireCuttersPlate)
                        .define('B', wireCuttersScrew)
                        .define('C', ModItemTagProvider.SCREWDRIVERS)
                        .define('F', ModItemTagProvider.FILES)
                        .unlockedBy("has_" + material + "_plate", has(wireCuttersPlate))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "wrench":
                Item wrenchPlate = getItem(material + "_plate");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern(" PH")
                        .pattern(" PP")
                        .pattern("P  ")
                        .define('P', wrenchPlate)
                        .define('H', ModItemTagProvider.HAMMERS)
                        .unlockedBy("has_" + material + "_plate", has(wrenchPlate))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "knife":
                Item knifeIngot = getItem(material + "_ingot");

                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern(" I ")
                        .pattern("S  ")
                        .pattern("   ")
                        .define('I', knifeIngot)
                        .define('S', Items.STICK)
                        .unlockedBy("has_iron_ingot", has(knifeIngot))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;

            case "mining_hammer":
                Item hammerIngot = getItem(material + "_ingot");
                Item hammerPlate = getItem(material + "_plate");
                Item handle = getItem("advanced_tool_handle");

                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("PPP")
                        .pattern("PIP")
                        .pattern(" H ")
                        .define('P', hammerPlate)
                        .define('I', hammerIngot)
                        .define('H', handle)
                        .unlockedBy("has_" + material + "_ingot", has(hammerIngot))
                        .save(recipeOutput, "tge_core:crafting/tool/" + material + "_" + toolType);
                break;
        }
    }

    private void registerGlassChain(RecipeOutput recipeOutput) {
        Item sand = Items.SAND;
        Item quartz_sand_dust = getItem("quartz_sand_dust");
        Item flint = Items.FLINT;
        Item flint_dust = getItem("flint_dust");
        Item glass_dust = getItem("glass_dust");
        Item glass = Items.GLASS;

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, flint_dust)
                .pattern(" F ")
                .pattern(" M ")
                .pattern("   ")
                .define('F', flint)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_mortar", has(ModItemTagProvider.MORTARS))
                .save(recipeOutput, "tge_core:crafting/non_metal/flint_dust");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, quartz_sand_dust)
                .pattern(" S ")
                .pattern(" M ")
                .pattern("   ")
                .define('S', sand)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_mortar", has(ModItemTagProvider.MORTARS))
                .save(recipeOutput, "tge_core:crafting/non_metal/quartz_sand_dust");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, glass_dust)
                .requires(quartz_sand_dust)
                .requires(flint_dust)
                .unlockedBy("has_sand", has(sand))
                .unlockedBy("has_flint", has(flint))
                .save(recipeOutput, "tge_core:crafting/non_metal/glass_dust");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(glass_dust), RecipeCategory.MISC, glass, 0, 200)
                .unlockedBy("has_glass_dust", has(glass_dust))
                .save(recipeOutput, "tge_core:campfire_cooking/glass");
    }

    private void registerCokeBrickChain(RecipeOutput recipeOutput) {
        Item clay = Items.CLAY_BALL;
        Item sand = Items.SAND;
        Item mold = getItem("brick_mold");
        Item wet_coke_brick = getItem("wet_coke_brick");
        Item coke_brick = getItem("coke_brick");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, mold)
                .pattern(" K ")
                .pattern("SSS")
                .pattern("   ")
                .define('K', ModItemTagProvider.KNIVES)
                .define('S', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_clay", has(clay))
                .unlockedBy("has_sand", has(sand))
                .save(recipeOutput, "tge_core:crafting/non_metal/brick_mold");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, wet_coke_brick, 6)
                .pattern("CCC")
                .pattern("SMS")
                .pattern("SSS")
                .define('C', clay)
                .define('S', sand)
                .define('M', mold)
                .unlockedBy("has_clay", has(clay))
                .unlockedBy("has_sand", has(sand))
                .save(recipeOutput, "tge_core:crafting/non_metal/wet_coke_brick");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(wet_coke_brick), RecipeCategory.MISC, coke_brick, 0.5f, 200)
                .unlockedBy("has_wet_coke_brick", has(wet_coke_brick))
                .save(recipeOutput, "tge_core:smelting/coke_brick");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(wet_coke_brick), RecipeCategory.MISC, coke_brick, 0.5f, 100)
                .unlockedBy("has_wet_coke_brick", has(wet_coke_brick))
                .save(recipeOutput, "tge_core:blasting/coke_brick");
    }

    private void registerFireBrickChain(RecipeOutput recipeOutput) {
        Item clay = Items.CLAY_BALL;
        Item clay_block = Items.CLAY;
        Item clay_dust = getItem("clay_dust");
        Item brick = Items.BRICK;
        Item bricks = Items.BRICKS;
        Item brick_dust = getItem("brick_dust");
        Item mold = getItem("brick_mold");
        Item fire_brick = getItem("fire_brick");
        Item fire_bricks = getItem("fire_bricks");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, clay_dust, 1)
                .pattern(" C ")
                .pattern(" M ")
                .pattern("   ")
                .define('C', clay)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_clay", has(clay))
                .save(recipeOutput, "tge_core:crafting/non_metal/clay_dust");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, clay_dust, 4)
                .pattern(" C ")
                .pattern(" M ")
                .pattern("   ")
                .define('C', clay_block)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_clay", has(clay))
                .save(recipeOutput, "tge_core:crafting/non_metal/clay_dust_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, brick_dust)
                .pattern(" B ")
                .pattern(" M ")
                .pattern("   ")
                .define('B', brick)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/non_metal/brick_dust");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, brick_dust, 4)
                .pattern(" B ")
                .pattern(" M ")
                .pattern("   ")
                .define('B', bricks)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_bricks", has(bricks))
                .save(recipeOutput, "tge_core:crafting/non_metal/brick_dust_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_brick, 2)
                .pattern(" B ")
                .pattern(" C ")
                .pattern(" M ")
                .define('B', brick_dust)
                .define('C', clay_dust)
                .define('M', mold)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/non_metal/fire_brick");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_brick, 2)
                .pattern(" C ")
                .pattern(" B ")
                .pattern(" M ")
                .define('B', brick_dust)
                .define('C', clay_dust)
                .define('M', mold)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/non_metal/fire_brick_2");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_bricks)
                .pattern("FF ")
                .pattern("FF ")
                .pattern("   ")
                .define('F', fire_brick)
                .unlockedBy("has_fire_brick", has(fire_brick))
                .save(recipeOutput, "tge_core:crafting/non_metal/fire_bricks");
    }
}