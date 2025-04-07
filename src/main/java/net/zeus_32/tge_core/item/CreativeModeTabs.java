package net.zeus_32.tge_core.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;

import java.util.function.Supplier;

public class CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TGECore.MODID);

    public static final Supplier<CreativeModeTab> TGE_METALS_TAB = CREATIVE_MODE_TAB.register("tge_metals_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BRASS_INGOT.get()))
                    .title(Component.translatable("creativetab.tge_core.tge_metals_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
                        output.accept(ModBlocks.LEAD_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_LEAD_ORE.get());
                        output.accept(ModBlocks.NICKEL_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_NICKEL_ORE.get());
                        output.accept(ModBlocks.SILVER_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SILVER_ORE.get());
                        output.accept(ModBlocks.OSMIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_OSMIUM_ORE.get());
                        output.accept(ModBlocks.TIN_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_TIN_ORE.get());
                        output.accept(ModBlocks.ZINC_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ZINC_ORE.get());

                        output.accept(ModItems.ALUMINIUM_INGOT);
                        output.accept(ModItems.ALUMINIUM_NUGGET);
                        output.accept(ModItems.ALUMINIUM_PLATE);
                        output.accept(ModItems.ALUMINIUM_ROD);
                        output.accept(ModItems.ALUMINIUM_GEAR);
                        output.accept(ModItems.ALUMINIUM_BOLT);
                        output.accept(ModItems.ALUMINIUM_SCREW);
                        output.accept(ModItems.ALUMINIUM_DUST);

                        output.accept(ModItems.BRASS_INGOT);
                        output.accept(ModItems.BRASS_NUGGET);
                        output.accept(ModItems.BRASS_PLATE);
                        output.accept(ModItems.BRASS_ROD);
                        output.accept(ModItems.BRASS_GEAR);
                        output.accept(ModItems.BRASS_BOLT);
                        output.accept(ModItems.BRASS_SCREW);
                        output.accept(ModItems.BRASS_DUST);

                        output.accept(ModItems.BRONZE_INGOT);
                        output.accept(ModItems.BRONZE_NUGGET);
                        output.accept(ModItems.BRONZE_PLATE);
                        output.accept(ModItems.BRONZE_ROD);
                        output.accept(ModItems.BRONZE_GEAR);
                        output.accept(ModItems.BRONZE_BOLT);
                        output.accept(ModItems.BRONZE_SCREW);
                        output.accept(ModItems.BRONZE_DUST);

                        output.accept(ModItems.COBALT_INGOT);
                        output.accept(ModItems.COBALT_NUGGET);
                        output.accept(ModItems.COBALT_PLATE);
                        output.accept(ModItems.COBALT_ROD);
                        output.accept(ModItems.COBALT_GEAR);
                        output.accept(ModItems.COBALT_BOLT);
                        output.accept(ModItems.COBALT_SCREW);
                        output.accept(ModItems.COBALT_DUST);

                        output.accept(ModItems.COPPER_NUGGET);
                        output.accept(ModItems.COPPER_PLATE);
                        output.accept(ModItems.COPPER_ROD);
                        output.accept(ModItems.COPPER_GEAR);
                        output.accept(ModItems.COPPER_BOLT);
                        output.accept(ModItems.COPPER_SCREW);
                        output.accept(ModItems.COPPER_DUST);

                        output.accept(ModItems.CUPRONICKEL_INGOT);
                        output.accept(ModItems.CUPRONICKEL_NUGGET);
                        output.accept(ModItems.CUPRONICKEL_PLATE);
                        output.accept(ModItems.CUPRONICKEL_ROD);
                        output.accept(ModItems.CUPRONICKEL_GEAR);
                        output.accept(ModItems.CUPRONICKEL_BOLT);
                        output.accept(ModItems.CUPRONICKEL_SCREW);
                        output.accept(ModItems.CUPRONICKEL_DUST);

                        output.accept(ModItems.ELECTRUM_INGOT);
                        output.accept(ModItems.ELECTRUM_NUGGET);
                        output.accept(ModItems.ELECTRUM_PLATE);
                        output.accept(ModItems.ELECTRUM_ROD);
                        output.accept(ModItems.ELECTRUM_GEAR);
                        output.accept(ModItems.ELECTRUM_BOLT);
                        output.accept(ModItems.ELECTRUM_SCREW);
                        output.accept(ModItems.ELECTRUM_DUST);

                        output.accept(ModItems.ENDERIUM_INGOT);
                        output.accept(ModItems.ENDERIUM_NUGGET);
                        output.accept(ModItems.ENDERIUM_PLATE);
                        output.accept(ModItems.ENDERIUM_ROD);
                        output.accept(ModItems.ENDERIUM_GEAR);
                        output.accept(ModItems.ENDERIUM_BOLT);
                        output.accept(ModItems.ENDERIUM_SCREW);
                        output.accept(ModItems.ENDERIUM_DUST);

                        output.accept(ModItems.GOLD_PLATE);
                        output.accept(ModItems.GOLD_ROD);
                        output.accept(ModItems.GOLD_GEAR);
                        output.accept(ModItems.GOLD_BOLT);
                        output.accept(ModItems.GOLD_SCREW);
                        output.accept(ModItems.GOLD_DUST);

                        output.accept(ModItems.INVAR_INGOT);
                        output.accept(ModItems.INVAR_NUGGET);
                        output.accept(ModItems.INVAR_PLATE);
                        output.accept(ModItems.INVAR_ROD);
                        output.accept(ModItems.INVAR_GEAR);
                        output.accept(ModItems.INVAR_BOLT);
                        output.accept(ModItems.INVAR_SCREW);
                        output.accept(ModItems.INVAR_DUST);

                        output.accept(ModItems.IRIDIUM_INGOT);
                        output.accept(ModItems.IRIDIUM_NUGGET);
                        output.accept(ModItems.IRIDIUM_PLATE);
                        output.accept(ModItems.IRIDIUM_ROD);
                        output.accept(ModItems.IRIDIUM_GEAR);
                        output.accept(ModItems.IRIDIUM_BOLT);
                        output.accept(ModItems.IRIDIUM_SCREW);
                        output.accept(ModItems.IRIDIUM_DUST);

                        output.accept(ModItems.IRON_PLATE);
                        output.accept(ModItems.IRON_ROD);
                        output.accept(ModItems.IRON_GEAR);
                        output.accept(ModItems.IRON_BOLT);
                        output.accept(ModItems.IRON_SCREW);
                        output.accept(ModItems.IRON_DUST);

                        output.accept(ModItems.LEAD_INGOT);
                        output.accept(ModItems.LEAD_NUGGET);
                        output.accept(ModItems.LEAD_PLATE);
                        output.accept(ModItems.LEAD_ROD);
                        output.accept(ModItems.LEAD_GEAR);
                        output.accept(ModItems.LEAD_BOLT);
                        output.accept(ModItems.LEAD_SCREW);
                        output.accept(ModItems.LEAD_DUST);

                        output.accept(ModItems.LUMIUM_INGOT);
                        output.accept(ModItems.LUMIUM_NUGGET);
                        output.accept(ModItems.LUMIUM_PLATE);
                        output.accept(ModItems.LUMIUM_ROD);
                        output.accept(ModItems.LUMIUM_GEAR);
                        output.accept(ModItems.LUMIUM_BOLT);
                        output.accept(ModItems.LUMIUM_SCREW);
                        output.accept(ModItems.LUMIUM_DUST);

                        output.accept(ModItems.NAQUADAH_INGOT);
                        output.accept(ModItems.NAQUADAH_NUGGET);
                        output.accept(ModItems.NAQUADAH_PLATE);
                        output.accept(ModItems.NAQUADAH_ROD);
                        output.accept(ModItems.NAQUADAH_GEAR);
                        output.accept(ModItems.NAQUADAH_BOLT);
                        output.accept(ModItems.NAQUADAH_SCREW);
                        output.accept(ModItems.NAQUADAH_DUST);

                        output.accept(ModItems.NICKEL_INGOT);
                        output.accept(ModItems.NICKEL_NUGGET);
                        output.accept(ModItems.NICKEL_PLATE);
                        output.accept(ModItems.NICKEL_ROD);
                        output.accept(ModItems.NICKEL_GEAR);
                        output.accept(ModItems.NICKEL_BOLT);
                        output.accept(ModItems.NICKEL_SCREW);
                        output.accept(ModItems.NICKEL_DUST);

                        output.accept(ModItems.OSMIUM_INGOT);
                        output.accept(ModItems.OSMIUM_NUGGET);
                        output.accept(ModItems.OSMIUM_PLATE);
                        output.accept(ModItems.OSMIUM_ROD);
                        output.accept(ModItems.OSMIUM_GEAR);
                        output.accept(ModItems.OSMIUM_BOLT);
                        output.accept(ModItems.OSMIUM_SCREW);
                        output.accept(ModItems.OSMIUM_DUST);

                        output.accept(ModItems.PLATINUM_INGOT);
                        output.accept(ModItems.PLATINUM_NUGGET);
                        output.accept(ModItems.PLATINUM_PLATE);
                        output.accept(ModItems.PLATINUM_ROD);
                        output.accept(ModItems.PLATINUM_GEAR);
                        output.accept(ModItems.PLATINUM_BOLT);
                        output.accept(ModItems.PLATINUM_SCREW);
                        output.accept(ModItems.PLATINUM_DUST);

                        output.accept(ModItems.RED_ALLOY_INGOT);
                        output.accept(ModItems.RED_ALLOY_NUGGET);
                        output.accept(ModItems.RED_ALLOY_PLATE);
                        output.accept(ModItems.RED_ALLOY_ROD);
                        output.accept(ModItems.RED_ALLOY_GEAR);
                        output.accept(ModItems.RED_ALLOY_BOLT);
                        output.accept(ModItems.RED_ALLOY_SCREW);
                        output.accept(ModItems.RED_ALLOY_DUST);

                        output.accept(ModItems.SILVER_INGOT);
                        output.accept(ModItems.SILVER_NUGGET);
                        output.accept(ModItems.SILVER_PLATE);
                        output.accept(ModItems.SILVER_ROD);
                        output.accept(ModItems.SILVER_GEAR);
                        output.accept(ModItems.SILVER_BOLT);
                        output.accept(ModItems.SILVER_SCREW);
                        output.accept(ModItems.SILVER_DUST);

                        output.accept(ModItems.SOUL_INFUSED_INGOT);
                        output.accept(ModItems.SOUL_INFUSED_NUGGET);
                        output.accept(ModItems.SOUL_INFUSED_PLATE);
                        output.accept(ModItems.SOUL_INFUSED_ROD);
                        output.accept(ModItems.SOUL_INFUSED_GEAR);
                        output.accept(ModItems.SOUL_INFUSED_BOLT);
                        output.accept(ModItems.SOUL_INFUSED_SCREW);
                        output.accept(ModItems.SOUL_INFUSED_DUST);

                        output.accept(ModItems.STAINLESS_STEEL_INGOT);
                        output.accept(ModItems.STAINLESS_STEEL_NUGGET);
                        output.accept(ModItems.STAINLESS_STEEL_PLATE);
                        output.accept(ModItems.STAINLESS_STEEL_ROD);
                        output.accept(ModItems.STAINLESS_STEEL_GEAR);
                        output.accept(ModItems.STAINLESS_STEEL_BOLT);
                        output.accept(ModItems.STAINLESS_STEEL_SCREW);
                        output.accept(ModItems.STAINLESS_STEEL_DUST);

                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.STEEL_NUGGET);
                        output.accept(ModItems.STEEL_PLATE);
                        output.accept(ModItems.STEEL_ROD);
                        output.accept(ModItems.STEEL_GEAR);
                        output.accept(ModItems.STEEL_BOLT);
                        output.accept(ModItems.STEEL_SCREW);
                        output.accept(ModItems.STEEL_DUST);

                        output.accept(ModItems.TIN_INGOT);
                        output.accept(ModItems.TIN_NUGGET);
                        output.accept(ModItems.TIN_PLATE);
                        output.accept(ModItems.TIN_ROD);
                        output.accept(ModItems.TIN_GEAR);
                        output.accept(ModItems.TIN_BOLT);
                        output.accept(ModItems.TIN_SCREW);
                        output.accept(ModItems.TIN_DUST);

                        output.accept(ModItems.TITANIUM_INGOT);
                        output.accept(ModItems.TITANIUM_NUGGET);
                        output.accept(ModItems.TITANIUM_PLATE);
                        output.accept(ModItems.TITANIUM_ROD);
                        output.accept(ModItems.TITANIUM_GEAR);
                        output.accept(ModItems.TITANIUM_BOLT);
                        output.accept(ModItems.TITANIUM_SCREW);
                        output.accept(ModItems.TITANIUM_DUST);

                        output.accept(ModItems.WROUGHT_IRON_INGOT);
                        output.accept(ModItems.WROUGHT_IRON_NUGGET);
                        output.accept(ModItems.WROUGHT_IRON_PLATE);
                        output.accept(ModItems.WROUGHT_IRON_ROD);
                        output.accept(ModItems.WROUGHT_IRON_GEAR);
                        output.accept(ModItems.WROUGHT_IRON_BOLT);
                        output.accept(ModItems.WROUGHT_IRON_SCREW);
                        output.accept(ModItems.WROUGHT_IRON_DUST);

                        output.accept(ModItems.ZINC_INGOT);
                        output.accept(ModItems.ZINC_NUGGET);
                        output.accept(ModItems.ZINC_PLATE);
                        output.accept(ModItems.ZINC_ROD);
                        output.accept(ModItems.ZINC_GEAR);
                        output.accept(ModItems.ZINC_BOLT);
                        output.accept(ModItems.ZINC_SCREW);
                        output.accept(ModItems.ZINC_DUST);

                    }).build());

    public static final Supplier<CreativeModeTab> TGE_NON_METALS_TAB = CREATIVE_MODE_TAB.register("tge_non_metals_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModNonMetalItems.GLASS_DUST.get()))
                    .title(Component.translatable("creativetab.tge_core.tge_non_metals_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlocks.RUBBER_LOG);
                        output.accept(ModBlocks.RUBBER_WOOD);
                        output.accept(ModBlocks.STRIPPED_RUBBER_LOG);
                        output.accept(ModBlocks.STRIPPED_RUBBER_WOOD);
                        output.accept(ModBlocks.RUBBER_PLANKS);
                        output.accept(ModBlocks.RUBBER_SAPLING);
                        output.accept(ModBlocks.RUBBER_LEAVES);
                        output.accept(ModNonMetalItems.STICKY_RESIN);

                        output.accept(ModNonMetalItems.WOODEN_GEAR);
                        output.accept(ModNonMetalItems.WATER_WHEEL_PADDLE);
                        output.accept(ModNonMetalItems.PLANT_GOO);

                        output.accept(ModNonMetalItems.FLINT_DUST);
                        output.accept(ModNonMetalItems.QUARTZ_SAND_DUST);
                        output.accept(ModNonMetalItems.GLASS_DUST);

                        output.accept(ModNonMetalItems.BRICK_MOLD);
                        output.accept(ModNonMetalItems.WET_COKE_BRICK);
                        output.accept(ModNonMetalItems.COKE_BRICK);

                        output.accept(ModNonMetalItems.BRICK_DUST);
                        output.accept(ModNonMetalItems.CLAY_DUST);
                        output.accept(ModNonMetalItems.FIRE_BRICK);
                        output.accept(ModBlocks.FIRE_BRICKS);

                        output.accept(ModItems.ANDESITE_COMPOUND);
                        output.accept(ModItems.ADVANCED_TOOL_HANDLE);

                        output.accept(ModNonMetalItems.DRILL_HEAD);
                        output.accept(ModNonMetalItems.SAW_BLADE);
                        output.accept(ModNonMetalItems.KINTETIC_MECHANISM);

                        output.accept(ModBlocks.STEEL_MACHINE_CASING);
                    }).build());

    public static final Supplier<CreativeModeTab> TGE_TOOLS_TAB = CREATIVE_MODE_TAB.register("tge_tools_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IRON_HAMMER.get()))
                    .title(Component.translatable("creativetab.tge_core.tge_tools_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ALUMINIUM_KNIFE);
                        output.accept(ModItems.ALUMINIUM_MINING_HAMMER);
                        output.accept(ModItems.ALUMINIUM_FILE);
                        output.accept(ModItems.ALUMINIUM_HAMMER);
                        output.accept(ModItems.ALUMINIUM_MORTAR);
                        output.accept(ModItems.ALUMINIUM_SAW);
                        output.accept(ModItems.ALUMINIUM_SCREWDRIVER);
                        output.accept(ModItems.ALUMINIUM_WIRE_CUTTERS);
                        output.accept(ModItems.ALUMINIUM_WRENCH);

                        output.accept(ModItems.BRONZE_KNIFE);
                        output.accept(ModItems.BRONZE_MINING_HAMMER);
                        output.accept(ModItems.BRONZE_FILE);
                        output.accept(ModItems.BRONZE_HAMMER);
                        output.accept(ModItems.BRONZE_MORTAR);
                        output.accept(ModItems.BRONZE_SAW);
                        output.accept(ModItems.BRONZE_SCREWDRIVER);
                        output.accept(ModItems.BRONZE_WIRE_CUTTERS);
                        output.accept(ModItems.BRONZE_WRENCH);

                        output.accept(ModItems.DIAMOND_KNIFE);
                        output.accept(ModItems.DIAMOND_MINING_HAMMER);
                        output.accept(ModItems.DIAMOND_FILE);
                        output.accept(ModItems.DIAMOND_HAMMER);
                        output.accept(ModItems.DIAMOND_MORTAR);
                        output.accept(ModItems.DIAMOND_SAW);
                        output.accept(ModItems.DIAMOND_SCREWDRIVER);
                        output.accept(ModItems.DIAMOND_WIRE_CUTTERS);
                        output.accept(ModItems.DIAMOND_WRENCH);

                        output.accept(ModItems.INVAR_KNIFE);
                        output.accept(ModItems.INVAR_MINING_HAMMER);
                        output.accept(ModItems.INVAR_FILE);
                        output.accept(ModItems.INVAR_HAMMER);
                        output.accept(ModItems.INVAR_MORTAR);
                        output.accept(ModItems.INVAR_SAW);
                        output.accept(ModItems.INVAR_SCREWDRIVER);
                        output.accept(ModItems.INVAR_WIRE_CUTTERS);
                        output.accept(ModItems.INVAR_WRENCH);

                        output.accept(ModItems.IRON_KNIFE);
                        output.accept(ModItems.IRON_MINING_HAMMER);
                        output.accept(ModItems.IRON_FILE);
                        output.accept(ModItems.IRON_HAMMER);
                        output.accept(ModItems.IRON_MORTAR);
                        output.accept(ModItems.IRON_SAW);
                        output.accept(ModItems.IRON_SCREWDRIVER);
                        output.accept(ModItems.IRON_WIRE_CUTTERS);
                        output.accept(ModItems.IRON_WRENCH);

                        output.accept(ModItems.STAINLESS_STEEL_KNIFE);
                        output.accept(ModItems.STAINLESS_STEEL_MINING_HAMMER);
                        output.accept(ModItems.STAINLESS_STEEL_FILE);
                        output.accept(ModItems.STAINLESS_STEEL_HAMMER);
                        output.accept(ModItems.STAINLESS_STEEL_MORTAR);
                        output.accept(ModItems.STAINLESS_STEEL_SAW);
                        output.accept(ModItems.STAINLESS_STEEL_SCREWDRIVER);
                        output.accept(ModItems.STAINLESS_STEEL_WIRE_CUTTERS);
                        output.accept(ModItems.STAINLESS_STEEL_WRENCH);

                        output.accept(ModItems.STEEL_KNIFE);
                        output.accept(ModItems.STEEL_MINING_HAMMER);
                        output.accept(ModItems.STEEL_FILE);
                        output.accept(ModItems.STEEL_HAMMER);
                        output.accept(ModItems.STEEL_MORTAR);
                        output.accept(ModItems.STEEL_SAW);
                        output.accept(ModItems.STEEL_SCREWDRIVER);
                        output.accept(ModItems.STEEL_WIRE_CUTTERS);
                        output.accept(ModItems.STEEL_WRENCH);

                        output.accept(ModItems.TITANIUM_KNIFE);
                        output.accept(ModItems.TITANIUM_MINING_HAMMER);
                        output.accept(ModItems.TITANIUM_FILE);
                        output.accept(ModItems.TITANIUM_HAMMER);
                        output.accept(ModItems.TITANIUM_MORTAR);
                        output.accept(ModItems.TITANIUM_SAW);
                        output.accept(ModItems.TITANIUM_SCREWDRIVER);
                        output.accept(ModItems.TITANIUM_WIRE_CUTTERS);
                        output.accept(ModItems.TITANIUM_WRENCH);

                        output.accept(ModItems.WROUGHT_IRON_KNIFE);
                        output.accept(ModItems.WROUGHT_IRON_MINING_HAMMER);
                        output.accept(ModItems.WROUGHT_IRON_FILE);
                        output.accept(ModItems.WROUGHT_IRON_HAMMER);
                        output.accept(ModItems.WROUGHT_IRON_MORTAR);
                        output.accept(ModItems.WROUGHT_IRON_SAW);
                        output.accept(ModItems.WROUGHT_IRON_SCREWDRIVER);
                        output.accept(ModItems.WROUGHT_IRON_WIRE_CUTTERS);
                        output.accept(ModItems.WROUGHT_IRON_WRENCH);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}