package net.zeus_32.tge_core.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.item.ModItems;
import net.zeus_32.tge_core.item.ModNonMetalItems;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<String> MATERIALS = Arrays.asList(
            "aluminium", "brass", "bronze", "cobalt", "copper",
            "cupronickel", "electrum", "enderium", "gold", "invar",
            "iridium", "iron", "lead", "lumium", "naquadah",
            "nickel", "osmium", "platinum", "red_alloy", "silver", "soul_infused",
            "stainless_steel", "steel", "tin", "titanium", "wrought_iron", "zinc"
    );

    private static final List<String> TOOL_MATERIALS = Arrays.asList(
            "aluminium", "bronze", "invar", "iron", "stainless_steel",
            "steel", "titanium", "wrought_iron"
    );

    private static final List<String> TOOL_TYPES = Arrays.asList(
            "file", "hammer", "mortar", "saw", "screwdriver",
            "wire_cutters", "wrench", "knife", "mining_hammer"
    );
    private static final List<String> RAW_MATERIALS = Arrays.asList(
            "aluminium", "lead", "nickel", "osmium", "silver",
            "tin", "zinc"
    );

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Register recipes for all materials
        for (String material : MATERIALS) {
            // Register component recipes
            registerPlateCrafting(recipeOutput, material);
            registerRodCrafting(recipeOutput, material);
            registerGearCrafting(recipeOutput, material);
            registerBoltCrafting(recipeOutput, material);
            registerScrewCrafting(recipeOutput, material);
            registerDustCrafting(recipeOutput, material);
            registerIngotFromDustSmelting(recipeOutput, material);

            // Skip vanilla nugget recipes as they exist in vanilla
            if (!List.of("iron", "gold", "copper").contains(material)) {
                registerIngotFromNuggetsRecipe(recipeOutput, material);
                registerNuggetsFromIngotRecipe(recipeOutput, material);
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

        for (String material : RAW_MATERIALS) {
            registerIngotFromRawSmelting(recipeOutput, material);
            registerIngotFromOreSmelting(recipeOutput, material);
        }

        registerShapedRecipes(recipeOutput);
        registerShapelessRecipes(recipeOutput);
        registerCookingRecipes(recipeOutput);

        registerGlassChain(recipeOutput);
        registerCokeBrickChain(recipeOutput);
        registerFireBrickChain(recipeOutput);

        registerEditedCreateRecipes(recipeOutput);
    }

    private Item getItem(String itemName) {
        try {
            return ModItems.ITEMS.getEntries().stream()
                    .filter(entry -> entry.getId().getPath().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Item not found in mod: " + itemName))
                    .get();
        } catch (IllegalStateException e) {
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
        TagKey<Item> STEEL_INGOT = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/steel"));
        TagKey<Item> STEEL_PLATE = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "plates/steel"));
        TagKey<Item> STEEL_ROD = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "rods/steel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModNonMetalItems.WOODEN_GEAR)
                .pattern("SPS")
                .pattern("PMP")
                .pattern("SPS")
                .define('S', Items.STICK)
                .define('P', ItemTags.PLANKS)
                .define('M', ModItemTagProvider.SAWS)
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput, "tge_core:crafting/wooden_gear");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModNonMetalItems.WATER_WHEEL_PADDLE)
                .pattern("PS ")
                .pattern("PSC")
                .pattern("PS ")
                .define('S', Items.STICK)
                .define('P', ItemTags.WOODEN_SLABS)
                .define('C', ModItemTagProvider.SAWS)
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
                .save(recipeOutput, "tge_core:crafting/water_wheel_paddle");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModNonMetalItems.PLANT_GOO)
                .pattern(" M ")
                .pattern("GGG")
                .pattern(" B ")
                .define('M', ModItemTagProvider.MORTARS)
                .define('G', Items.KELP)
                .define('B', Items.BOWL)
                .unlockedBy("has_kelp", has(Items.KELP))
                .unlockedBy("has_mortar", has(ModItemTagProvider.MORTARS))
                .save(recipeOutput, "tge_core:crafting/plant_goo");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ANDESITE_COMPOUND, 4)
                .pattern("GAG")
                .pattern("AHA")
                .pattern("GAG")
                .define('G', ModNonMetalItems.PLANT_GOO)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_plant_goo", has(ModNonMetalItems.PLANT_GOO))
                .unlockedBy("has_andesite_alloy", has(AllItems.ANDESITE_ALLOY))
                .save(recipeOutput, "tge_core:crafting/andesite_compound");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCED_TOOL_HANDLE)
                .pattern(" LR")
                .pattern("LRL")
                .pattern("RL ")
                .define('L', Items.LEATHER)
                .define('R', STEEL_ROD)
                .unlockedBy("has_steel_ingot", has(STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/tool/advanced_tool_handle");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_MACHINE_CASING)
                .pattern("IPI")
                .pattern("PWP")
                .pattern("IPI")
                .define('I', STEEL_INGOT)
                .define('P', STEEL_PLATE)
                .define('W', ModItemTagProvider.WRENCHES)
                .unlockedBy("has_steel_ingot", has(STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/steel_machine_casing");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModNonMetalItems.DRILL_HEAD)
                .pattern(" I ")
                .pattern("IAI")
                .pattern("IHI")
                .define('I', STEEL_INGOT)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_steel_ingot", has(STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/drill_head");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModNonMetalItems.SAW_BLADE)
                .pattern(" P ")
                .pattern("PAP")
                .pattern(" P ")
                .define('P', STEEL_PLATE)
                .define('A', AllItems.ANDESITE_ALLOY)
                .unlockedBy("has_steel_ingot", has(STEEL_INGOT))
                .save(recipeOutput, "tge_core:crafting/saw_blade");
    }
    private void registerShapelessRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModNonMetalItems.KINTETIC_MECHANISM)
                .requires(AllBlocks.COGWHEEL)
                .requires(Tags.Items.STRIPPED_LOGS)
                .requires(Tags.Items.STRIPPED_LOGS)
                .requires(ModItems.ANDESITE_COMPOUND)
                .requires(ModItems.ANDESITE_COMPOUND)
                .requires(ModItemTagProvider.SAWS)
                .requires(ModItemTagProvider.HAMMERS)
                .unlockedBy("has_cogwheel", has(AllBlocks.COGWHEEL))
                .save(recipeOutput, "tge_core:crafting/kinetic_mechanism");
    }

    private void registerCookingRecipes(RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.campfireCooking(
                Ingredient.of(Items.IRON_INGOT),
                RecipeCategory.MISC,
                ModItems.WROUGHT_IRON_INGOT,
                0.0f,
                200)
                .unlockedBy("has_quartz_sand_dust", has(ModNonMetalItems.QUARTZ_SAND_DUST))
                .save(recipeOutput, "tge_core:campfire_cooking/quartz_sand");

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(Items.SLIME_BALL),
                RecipeCategory.MISC,
                ModNonMetalItems.STICKY_RESIN,
                0.6f,
                200)
                .unlockedBy("has_slime", has(Items.SLIME_BALL))
                .save(recipeOutput, "tge_core:smelting/sticky_resin");
        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(Items.SLIME_BALL),
                RecipeCategory.MISC,
                ModNonMetalItems.STICKY_RESIN,
                0.6f,
                100)
                .unlockedBy("has_slime", has(Items.SLIME_BALL))
                .save(recipeOutput, "tge_core:blasting/sticky_resin");
    }

    private void registerEditedCreateRecipes(RecipeOutput recipeOutput) {
        TagKey<Item> STEEL_PLATE = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "plates/steel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.SHAFT, 4)
                .pattern("A")
                .pattern("A")
                .define('A', AllItems.ANDESITE_ALLOY)
                .unlockedBy("has_andesite_alloy", has(AllItems.ANDESITE_ALLOY))
                .save(recipeOutput, "create:crafting/kinetics/shaft");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.PROPELLER)
                .pattern(" S ")
                .pattern("SAS")
                .pattern(" S ")
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('S', STEEL_PLATE)
                .unlockedBy("has_andesite_alloy", has(AllItems.ANDESITE_ALLOY))
                .save(recipeOutput, "create:crafting/kinetics/propeller");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.ENCASED_FAN)
                .pattern(" P ")
                .pattern("MAM")
                .pattern("WSH")
                .define('P', AllBlocks.SHAFT)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM)
                .define('A', AllBlocks.ANDESITE_CASING)
                .define('S', AllItems.PROPELLER)
                .define('W', ModItemTagProvider.WRENCHES)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_andesite_alloy", has(AllItems.ANDESITE_ALLOY))
                .save(recipeOutput, "create:crafting/kinetics/encased_fan");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.COGWHEEL, 2)
                .pattern("BSB")
                .pattern("PGP")
                .pattern("BSB")
                .define('B', ItemTags.WOODEN_BUTTONS)
                .define('S', AllBlocks.SHAFT)
                .define('P', ItemTags.PLANKS)
                .define('G', ModNonMetalItems.WOODEN_GEAR)
                .unlockedBy("has_wooden_gear", has(ModNonMetalItems.WOODEN_GEAR))
                .save(recipeOutput, "create:crafting/kinetics/cogwheel");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LARGE_COGWHEEL)
                .pattern("BBB")
                .pattern("BGB")
                .pattern("BBB")
                .define('B', ItemTags.WOODEN_BUTTONS)
                .define('G', AllBlocks.COGWHEEL)
                .unlockedBy("has_cogwheel", has(AllBlocks.COGWHEEL))
                .save(recipeOutput, "create:crafting/kinetics/large_cogwheel");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.WATER_WHEEL)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', ModNonMetalItems.WATER_WHEEL_PADDLE)
                .define('P', ModNonMetalItems.WOODEN_GEAR)
                .unlockedBy("has_wooden_gear", has(ModNonMetalItems.WOODEN_GEAR))
                .save(recipeOutput, "create:crafting/kinetics/water_wheel");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LARGE_WATER_WHEEL)
                .pattern("GGG")
                .pattern("GPG")
                .pattern("GGG")
                .define('G', ModNonMetalItems.WATER_WHEEL_PADDLE)
                .define('P', AllBlocks.WATER_WHEEL)
                .unlockedBy("has_water_wheel", has(AllBlocks.WATER_WHEEL))
                .save(recipeOutput, "create:crafting/kinetics/large_water_wheel");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MECHANICAL_PRESS)
                .pattern(" S ")
                .pattern("MCM")
                .pattern("WIH")
                .define('S', AllBlocks.COGWHEEL)
                .define('C', AllBlocks.ANDESITE_CASING)
                .define('I', Blocks.IRON_BLOCK)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM.get())
                .define('W', ModItemTagProvider.WRENCHES)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_casing", has(AllBlocks.ANDESITE_CASING))
                .unlockedBy("has_mechanism", has(ModNonMetalItems.KINTETIC_MECHANISM.get()))
                .save(recipeOutput, "create:crafting/kinetics/mechanical_press");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MECHANICAL_MIXER)
                .pattern(" S ")
                .pattern("MCM")
                .pattern("WIH")
                .define('S', AllBlocks.COGWHEEL)
                .define('C', AllBlocks.ANDESITE_CASING)
                .define('I', AllItems.WHISK)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM.get())
                .define('W', ModItemTagProvider.WRENCHES)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_casing", has(AllBlocks.ANDESITE_CASING))
                .unlockedBy("has_mechanism", has(ModNonMetalItems.KINTETIC_MECHANISM.get()))
                .save(recipeOutput, "create:crafting/kinetics/mechanical_mixer");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MECHANICAL_DRILL)
                .pattern(" D ")
                .pattern("MAM")
                .pattern("WSH")
                .define('D', ModNonMetalItems.DRILL_HEAD)
                .define('S', AllBlocks.SHAFT)
                .define('A', AllBlocks.ANDESITE_CASING)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM.get())
                .define('W', ModItemTagProvider.WRENCHES)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_casing", has(AllBlocks.ANDESITE_CASING))
                .unlockedBy("has_mechanism", has(ModNonMetalItems.KINTETIC_MECHANISM.get()))
                .save(recipeOutput, "create:crafting/kinetics/mechanical_drill");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.MECHANICAL_SAW)
                .pattern(" D ")
                .pattern("MAM")
                .pattern("WSH")
                .define('D', ModNonMetalItems.SAW_BLADE)
                .define('S', AllBlocks.SHAFT)
                .define('A', AllBlocks.ANDESITE_CASING)
                .define('M', ModNonMetalItems.KINTETIC_MECHANISM.get())
                .define('W', ModItemTagProvider.WRENCHES)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_casing", has(AllBlocks.ANDESITE_CASING))
                .unlockedBy("has_mechanism", has(ModNonMetalItems.KINTETIC_MECHANISM.get()))
                .save(recipeOutput, "create:crafting/kinetics/mechanical_saw");
    }

    private void registerPlateCrafting(RecipeOutput recipeOutput, String material) {
        Item plate = getItem(material + "_plate");
        TagKey<Item> ingotTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + material));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, plate)
                .pattern("H")
                .pattern("I")
                .pattern("I")
                .define('H', ModItemTagProvider.HAMMERS)
                .define('I', ingotTag)
                .unlockedBy("has_" + material + "_ingot", has(ingotTag))
                .save(recipeOutput, "tge_core:crafting/metal/plate/" + material + "_plate");
    }

    private void registerRodCrafting(RecipeOutput recipeOutput, String material) {
        Item rod = getItem(material + "_rod");
        TagKey<Item> ingotTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + material));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, rod, 2)
                .pattern("F ")
                .pattern(" I")
                .define('F', ModItemTagProvider.FILES)
                .define('I', ingotTag)
                .unlockedBy("has_" + material + "_ingot", has(ingotTag))
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
                .pattern("S ")
                .pattern(" R")
                .define('R', rod)
                .define('S', ModItemTagProvider.SAWS)
                .unlockedBy("has_" + material + "_rod", has(rod))
                .save(recipeOutput, "tge_core:crafting/metal/bolt/" + material + "_bolt");
    }

    private void registerScrewCrafting(RecipeOutput recipeOutput, String material) {
        Item screw = getItem(material + "_screw");
        Item bolt = getItem(material + "_bolt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, screw)
                .pattern("HB")
                .pattern("B ")
                .define('B', bolt)
                .define('H', ModItemTagProvider.HAMMERS)
                .unlockedBy("has_" + material + "_bolt", has(bolt))
                .save(recipeOutput, "tge_core:crafting/metal/screw/" + material + "_screw");
    }

    private void registerIngotFromNuggetsRecipe(RecipeOutput recipeOutput, String material) {
        Item ingot = getItem(material + "_ingot");
        TagKey<Item> nuggetTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/" + material));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', nuggetTag)
                .unlockedBy("has_" + material + "_nugget", has(nuggetTag))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/" + material + "_ingot_from_nuggets");
    }

    private void registerIngotFromDustSmelting(RecipeOutput recipeOutput, String material) {
        Item ingot = getItem(material + "_ingot");
        TagKey<Item> dustTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "dusts/" + material));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(dustTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                200)
                .unlockedBy("has_" + material + "_dust", has(dustTag))
                .save(recipeOutput, "tge_core:smelting/metal/ingot/" + material + "_ingot_from_dust");

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(dustTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                100)
                .unlockedBy("has_" + material + "_dust", has(dustTag))
                .save(recipeOutput, "tge_core:blasting/metal/ingot/" + material + "_ingot_from_dust");
    }

    private void registerNuggetsFromIngotRecipe(RecipeOutput recipeOutput, String material) {
        TagKey<Item> nuggetTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/" + material));
        TagKey<Item> ingotTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + material));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, getItem(material + "_nugget"), 9)
                .requires(ingotTag)
                .unlockedBy("has_" + material + "_ingot", has(ingotTag))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/" + material + "_nuggets_from_ingot");
    }

    private void registerDustCrafting(RecipeOutput recipeOutput, String material) {
        Item dust = getItem(material + "_dust");
        TagKey<Item> ingot = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + material));
        TagKey<Item> raw = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "raw_materials/" + material));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, dust)
                .pattern("I")
                .pattern("M")
                .define('I', ingot)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_" + material + "_ingot", has(ingot))
                .save(recipeOutput, "tge_core:crafting/metal/dust/" + material + "_dust_from_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, dust)
                .pattern("I")
                .pattern("M")
                .define('I', raw)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_raw_" + material, has(raw))
                .save(recipeOutput, "tge_core:crafting/metal/dust/" + material + "_dust_from_raw_material");
    }
    private void registerIngotFromRawSmelting(RecipeOutput recipeOutput, String material) {
        Item ingot = getItem(material + "_ingot");
        TagKey<Item> rawTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "raw_materials/" + material));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(rawTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                200)
                .unlockedBy("has_raw_" + material, has(rawTag))
                .save(recipeOutput, "tge_core:smelting/metal/ingot/" + material + "_ingot_from_raw_material");

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(rawTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                100)
                .unlockedBy("has_raw_" + material, has(rawTag))
                .save(recipeOutput, "tge_core:blasting/metal/ingot/" + material + "_ingot_from_raw_material");
    }
    private void registerIngotFromOreSmelting(RecipeOutput recipeOutput, String material) {
        Item ingot = getItem(material + "_ingot");
        TagKey<Item> oreTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ores/" + material));

        SimpleCookingRecipeBuilder.smelting(
                Ingredient.of(oreTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                200)
                .unlockedBy("has_" + material + "_ore", has(oreTag))
                .save(recipeOutput, "tge_core:smelting/metal/ingot/" + material + "_ingot_from_ore");

        SimpleCookingRecipeBuilder.blasting(
                Ingredient.of(oreTag),
                RecipeCategory.MISC,
                ingot,
                0.6f,
                100)
                .unlockedBy("has_" + material + "_ore", has(oreTag))
                .save(recipeOutput, "tge_core:blasting/metal/ingot/" + material + "_ingot_from_ore");
    }

    private void registerCopperNuggetsFromIngotRecipe(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, getItem("copper_nugget"), 9)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER))
                .save(recipeOutput, "tge_core:crafting/metal/nugget/copper_nuggets_from_ingot");
    }

    private void registerCopperIngotFromNuggetsRecipe(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.COPPER_INGOT)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/copper")))
                .unlockedBy("has_copper_nugget", has(ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/copper"))))
                .save(recipeOutput, "tge_core:crafting/metal/ingot/copper_ingot_from_nuggets");
    }

    private void registerToolsRecipe(RecipeOutput recipeOutput, String material, String toolType) {
        Item tool = getItem(material + "_" + toolType);

        switch (toolType) {
            case "file":
                Item plate = getItem(material + "_plate");
                ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, tool)
                        .pattern("I")
                        .pattern("I")
                        .pattern("S")
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
                        .pattern(" I")
                        .pattern("S ")
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
                .pattern("F")
                .pattern("M")
                .define('F', flint)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_mortar", has(ModItemTagProvider.MORTARS))
                .save(recipeOutput, "tge_core:crafting/flint_dust");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, quartz_sand_dust)
                .pattern("S")
                .pattern("M")
                .define('S', sand)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_mortar", has(ModItemTagProvider.MORTARS))
                .save(recipeOutput, "tge_core:crafting/quartz_sand_dust");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, glass_dust)
                .requires(quartz_sand_dust)
                .requires(flint_dust)
                .unlockedBy("has_sand", has(sand))
                .unlockedBy("has_flint", has(flint))
                .save(recipeOutput, "tge_core:crafting/glass_dust");

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
                .define('K', ModItemTagProvider.KNIVES)
                .define('S', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_clay", has(clay))
                .unlockedBy("has_sand", has(sand))
                .save(recipeOutput, "tge_core:crafting/brick_mold");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, wet_coke_brick, 6)
                .pattern("CCC")
                .pattern("SMS")
                .pattern("SSS")
                .define('C', clay)
                .define('S', sand)
                .define('M', mold)
                .unlockedBy("has_clay", has(clay))
                .unlockedBy("has_sand", has(sand))
                .save(recipeOutput, "tge_core:crafting/wet_coke_brick");

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
                .pattern("C")
                .pattern("M")
                .define('C', clay)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_clay", has(clay))
                .save(recipeOutput, "tge_core:crafting/clay_dust");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, clay_dust, 4)
                .pattern("C")
                .pattern("M")
                .define('C', clay_block)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_clay", has(clay))
                .save(recipeOutput, "tge_core:crafting/clay_dust_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, brick_dust)
                .pattern("B")
                .pattern("M")
                .define('B', brick)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/brick_dust");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, brick_dust, 4)
                .pattern("B")
                .pattern("M")
                .define('B', bricks)
                .define('M', ModItemTagProvider.MORTARS)
                .unlockedBy("has_bricks", has(bricks))
                .save(recipeOutput, "tge_core:crafting/brick_dust_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_brick, 2)
                .pattern("B")
                .pattern("C")
                .pattern("M")
                .define('B', brick_dust)
                .define('C', clay_dust)
                .define('M', mold)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/fire_brick");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_brick, 2)
                .pattern("C")
                .pattern("B")
                .pattern("M")
                .define('B', brick_dust)
                .define('C', clay_dust)
                .define('M', mold)
                .unlockedBy("has_brick", has(brick))
                .save(recipeOutput, "tge_core:crafting/fire_brick_2");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, fire_bricks)
                .pattern("FF")
                .pattern("FF")
                .define('F', fire_brick)
                .unlockedBy("has_fire_brick", has(fire_brick))
                .save(recipeOutput, "tge_core:crafting/fire_bricks");
    }
}