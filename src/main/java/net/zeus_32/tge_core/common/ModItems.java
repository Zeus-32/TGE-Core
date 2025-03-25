package net.zeus_32.tge_core.common;

import net.minecraft.world.item.Item;
import net.zeus_32.tge_core.TGECore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.common.tool.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

    public static final DeferredItem<Item> ALUMINIUM_INGOT = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_INGOT = registerIngot("brass");
    public static final DeferredItem<Item> BRONZE_INGOT = registerIngot("bronze");
    public static final DeferredItem<Item> COBALT_INGOT = registerIngot("cobalt");
    public static final DeferredItem<Item> CUPRONICKEL_INGOT = registerIngot("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_INGOT = registerIngot("electrum");
    public static final DeferredItem<Item> ENDERIUM_INGOT = registerIngot("enderium");
    public static final DeferredItem<Item> INVAR_INGOT = registerIngot("invar");
    public static final DeferredItem<Item> IRIDIUM_INGOT = registerIngot("iridium");
    public static final DeferredItem<Item> LEAD_INGOT = registerIngot("lead");
    public static final DeferredItem<Item> LUMIUM_INGOT = registerIngot("lumium");
    public static final DeferredItem<Item> NAQUADAH_INGOT = registerIngot("naquadah");
    public static final DeferredItem<Item> NICKEL_INGOT = registerIngot("nickel");
    public static final DeferredItem<Item> OSMIUM_INGOT = registerIngot("osmium");
    public static final DeferredItem<Item> PLATINUM_INGOT = registerIngot("platinum");
    public static final DeferredItem<Item> PLUTONIUM_INGOT = registerIngot("plutonium");
    public static final DeferredItem<Item> POLONIUM_INGOT = registerIngot("polonium");
    public static final DeferredItem<Item> RED_ALLOY_INGOT = registerIngot("red_alloy");
    public static final DeferredItem<Item> SILVER_INGOT = registerIngot("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_INGOT = registerIngot("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_INGOT = registerIngot("stainless_steel");
    public static final DeferredItem<Item> STEEL_INGOT = registerIngot("steel");
    public static final DeferredItem<Item> TIN_INGOT = registerIngot("tin");
    public static final DeferredItem<Item> TITANIUM_INGOT = registerIngot("titanium");
    public static final DeferredItem<Item> URANIUM_INGOT = registerIngot("uranium");
    public static final DeferredItem<Item> WROUGHT_IRON_INGOT = registerIngot("wrought_iron");
    public static final DeferredItem<Item> ZINC_INGOT = registerIngot("zinc");

    public static final DeferredItem<Item> ALUMINIUM_NUGGET = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_NUGGET = registerNugget("brass");
    public static final DeferredItem<Item> BRONZE_NUGGET = registerNugget("bronze");
    public static final DeferredItem<Item> COBALT_NUGGET = registerNugget("cobalt");
    public static final DeferredItem<Item> COPPER_NUGGET = registerNugget("copper");
    public static final DeferredItem<Item> CUPRONICKEL_NUGGET = registerNugget("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_NUGGET = registerNugget("electrum");
    public static final DeferredItem<Item> ENDERIUM_NUGGET = registerNugget("enderium");
    public static final DeferredItem<Item> INVAR_NUGGET = registerNugget("invar");
    public static final DeferredItem<Item> IRIDIUM_NUGGET = registerNugget("iridium");
    public static final DeferredItem<Item> LEAD_NUGGET = registerNugget("lead");
    public static final DeferredItem<Item> LUMIUM_NUGGET = registerNugget("lumium");
    public static final DeferredItem<Item> NAQUADAH_NUGGET = registerNugget("naquadah");
    public static final DeferredItem<Item> NICKEL_NUGGET = registerNugget("nickel");
    public static final DeferredItem<Item> OSMIUM_NUGGET = registerNugget("osmium");
    public static final DeferredItem<Item> PLATINUM_NUGGET = registerNugget("platinum");
    public static final DeferredItem<Item> RED_ALLOY_NUGGET = registerNugget("red_alloy");
    public static final DeferredItem<Item> SILVER_NUGGET = registerNugget("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_NUGGET = registerNugget("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_NUGGET = registerNugget("stainless_steel");
    public static final DeferredItem<Item> STEEL_NUGGET = registerNugget("steel");
    public static final DeferredItem<Item> TIN_NUGGET = registerNugget("tin");
    public static final DeferredItem<Item> TITANIUM_NUGGET = registerNugget("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_NUGGET = registerNugget("wrought_iron");
    public static final DeferredItem<Item> ZINC_NUGGET = registerNugget("zinc");

    public static final DeferredItem<Item> ALUMINIUM_PLATE = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_PLATE = registerPlate("brass");
    public static final DeferredItem<Item> BRONZE_PLATE = registerPlate("bronze");
    public static final DeferredItem<Item> COBALT_PLATE = registerPlate("cobalt");
    public static final DeferredItem<Item> COPPER_PLATE = registerPlate("copper");
    public static final DeferredItem<Item> CUPRONICKEL_PLATE = registerPlate("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_PLATE = registerPlate("electrum");
    public static final DeferredItem<Item> ENDERIUM_PLATE = registerPlate("enderium");
    public static final DeferredItem<Item> GOLD_PLATE = registerPlate("gold");
    public static final DeferredItem<Item> INVAR_PLATE = registerPlate("invar");
    public static final DeferredItem<Item> IRIDIUM_PLATE = registerPlate("iridium");
    public static final DeferredItem<Item> IRON_PLATE = registerPlate("iron");
    public static final DeferredItem<Item> LEAD_PLATE = registerPlate("lead");
    public static final DeferredItem<Item> LUMIUM_PLATE = registerPlate("lumium");
    public static final DeferredItem<Item> NAQUADAH_PLATE = registerPlate("naquadah");
    public static final DeferredItem<Item> NICKEL_PLATE = registerPlate("nickel");
    public static final DeferredItem<Item> OSMIUM_PLATE = registerPlate("osmium");
    public static final DeferredItem<Item> PLATINUM_PLATE = registerPlate("platinum");
    public static final DeferredItem<Item> RED_ALLOY_PLATE = registerPlate("red_alloy");
    public static final DeferredItem<Item> SILVER_PLATE = registerPlate("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_PLATE = registerPlate("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_PLATE = registerPlate("stainless_steel");
    public static final DeferredItem<Item> STEEL_PLATE = registerPlate("steel");
    public static final DeferredItem<Item> TIN_PLATE = registerPlate("tin");
    public static final DeferredItem<Item> TITANIUM_PLATE = registerPlate("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_PLATE = registerPlate("wrought_iron");
    public static final DeferredItem<Item> ZINC_PLATE = registerPlate("zinc");

    public static final DeferredItem<Item> ALUMINIUM_ROD = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_ROD = registerRod("brass");
    public static final DeferredItem<Item> BRONZE_ROD = registerRod("bronze");
    public static final DeferredItem<Item> COBALT_ROD = registerRod("cobalt");
    public static final DeferredItem<Item> COPPER_ROD = registerRod("copper");
    public static final DeferredItem<Item> CUPRONICKEL_ROD = registerRod("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_ROD = registerRod("electrum");
    public static final DeferredItem<Item> ENDERIUM_ROD = registerRod("enderium");
    public static final DeferredItem<Item> GOLD_ROD = registerRod("gold");
    public static final DeferredItem<Item> INVAR_ROD = registerRod("invar");
    public static final DeferredItem<Item> IRIDIUM_ROD = registerRod("iridium");
    public static final DeferredItem<Item> IRON_ROD = registerRod("iron");
    public static final DeferredItem<Item> LEAD_ROD = registerRod("lead");
    public static final DeferredItem<Item> LUMIUM_ROD = registerRod("lumium");
    public static final DeferredItem<Item> NAQUADAH_ROD = registerRod("naquadah");
    public static final DeferredItem<Item> NICKEL_ROD = registerRod("nickel");
    public static final DeferredItem<Item> OSMIUM_ROD = registerRod("osmium");
    public static final DeferredItem<Item> PLATINUM_ROD = registerRod("platinum");
    public static final DeferredItem<Item> RED_ALLOY_ROD = registerRod("red_alloy");
    public static final DeferredItem<Item> SILVER_ROD = registerRod("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_ROD = registerRod("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_ROD = registerRod("stainless_steel");
    public static final DeferredItem<Item> STEEL_ROD = registerRod("steel");
    public static final DeferredItem<Item> TIN_ROD = registerRod("tin");
    public static final DeferredItem<Item> TITANIUM_ROD = registerRod("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_ROD = registerRod("wrought_iron");
    public static final DeferredItem<Item> ZINC_ROD = registerRod("zinc");

    public static final DeferredItem<Item> ALUMINIUM_GEAR = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_GEAR = registerGear("brass");
    public static final DeferredItem<Item> BRONZE_GEAR = registerGear("bronze");
    public static final DeferredItem<Item> COBALT_GEAR = registerGear("cobalt");
    public static final DeferredItem<Item> COPPER_GEAR = registerGear("copper");
    public static final DeferredItem<Item> CUPRONICKEL_GEAR = registerGear("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_GEAR = registerGear("electrum");
    public static final DeferredItem<Item> ENDERIUM_GEAR = registerGear("enderium");
    public static final DeferredItem<Item> GOLD_GEAR = registerGear("gold");
    public static final DeferredItem<Item> INVAR_GEAR = registerGear("invar");
    public static final DeferredItem<Item> IRIDIUM_GEAR = registerGear("iridium");
    public static final DeferredItem<Item> IRON_GEAR = registerGear("iron");
    public static final DeferredItem<Item> LEAD_GEAR = registerGear("lead");
    public static final DeferredItem<Item> LUMIUM_GEAR = registerGear("lumium");
    public static final DeferredItem<Item> NAQUADAH_GEAR = registerGear("naquadah");
    public static final DeferredItem<Item> NICKEL_GEAR = registerGear("nickel");
    public static final DeferredItem<Item> OSMIUM_GEAR = registerGear("osmium");
    public static final DeferredItem<Item> PLATINUM_GEAR = registerGear("platinum");
    public static final DeferredItem<Item> RED_ALLOY_GEAR = registerGear("red_alloy");
    public static final DeferredItem<Item> SILVER_GEAR = registerGear("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_GEAR = registerGear("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_GEAR = registerGear("stainless_steel");
    public static final DeferredItem<Item> STEEL_GEAR = registerGear("steel");
    public static final DeferredItem<Item> TIN_GEAR = registerGear("tin");
    public static final DeferredItem<Item> TITANIUM_GEAR = registerGear("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_GEAR = registerGear("wrought_iron");
    public static final DeferredItem<Item> ZINC_GEAR = registerGear("zinc");

    public static final DeferredItem<Item> ALUMINIUM_BOLT = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_BOLT = registerBolt("brass");
    public static final DeferredItem<Item> BRONZE_BOLT = registerBolt("bronze");
    public static final DeferredItem<Item> COBALT_BOLT = registerBolt("cobalt");
    public static final DeferredItem<Item> COPPER_BOLT = registerBolt("copper");
    public static final DeferredItem<Item> CUPRONICKEL_BOLT = registerBolt("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_BOLT = registerBolt("electrum");
    public static final DeferredItem<Item> ENDERIUM_BOLT = registerBolt("enderium");
    public static final DeferredItem<Item> GOLD_BOLT = registerBolt("gold");
    public static final DeferredItem<Item> INVAR_BOLT = registerBolt("invar");
    public static final DeferredItem<Item> IRIDIUM_BOLT = registerBolt("iridium");
    public static final DeferredItem<Item> IRON_BOLT = registerBolt("iron");
    public static final DeferredItem<Item> LEAD_BOLT = registerBolt("lead");
    public static final DeferredItem<Item> LUMIUM_BOLT = registerBolt("lumium");
    public static final DeferredItem<Item> NAQUADAH_BOLT = registerBolt("naquadah");
    public static final DeferredItem<Item> NICKEL_BOLT = registerBolt("nickel");
    public static final DeferredItem<Item> OSMIUM_BOLT = registerBolt("osmium");
    public static final DeferredItem<Item> PLATINUM_BOLT = registerBolt("platinum");
    public static final DeferredItem<Item> RED_ALLOY_BOLT = registerBolt("red_alloy");
    public static final DeferredItem<Item> SILVER_BOLT = registerBolt("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_BOLT = registerBolt("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_BOLT = registerBolt("stainless_steel");
    public static final DeferredItem<Item> STEEL_BOLT = registerBolt("steel");
    public static final DeferredItem<Item> TIN_BOLT = registerBolt("tin");
    public static final DeferredItem<Item> TITANIUM_BOLT = registerBolt("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_BOLT = registerBolt("wrought_iron");
    public static final DeferredItem<Item> ZINC_BOLT = registerBolt("zinc");

    public static final DeferredItem<Item> ALUMINIUM_SCREW = registerIngot("aluminium");
    public static final DeferredItem<Item> BRASS_SCREW = registerScrew("brass");
    public static final DeferredItem<Item> BRONZE_SCREW = registerScrew("bronze");
    public static final DeferredItem<Item> COBALT_SCREW = registerScrew("cobalt");
    public static final DeferredItem<Item> COPPER_SCREW = registerScrew("copper");
    public static final DeferredItem<Item> CUPRONICKEL_SCREW = registerScrew("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_SCREW = registerScrew("electrum");
    public static final DeferredItem<Item> ENDERIUM_SCREW = registerScrew("enderium");
    public static final DeferredItem<Item> GOLD_SCREW = registerScrew("gold");
    public static final DeferredItem<Item> INVAR_SCREW = registerScrew("invar");
    public static final DeferredItem<Item> IRIDIUM_SCREW = registerScrew("iridium");
    public static final DeferredItem<Item> IRON_SCREW = registerScrew("iron");
    public static final DeferredItem<Item> LEAD_SCREW = registerScrew("lead");
    public static final DeferredItem<Item> LUMIUM_SCREW = registerScrew("lumium");
    public static final DeferredItem<Item> NAQUADAH_SCREW = registerScrew("naquadah");
    public static final DeferredItem<Item> NICKEL_SCREW = registerScrew("nickel");
    public static final DeferredItem<Item> OSMIUM_SCREW = registerScrew("osmium");
    public static final DeferredItem<Item> PLATINUM_SCREW = registerScrew("platinum");
    public static final DeferredItem<Item> RED_ALLOY_SCREW = registerScrew("red_alloy");
    public static final DeferredItem<Item> SILVER_SCREW = registerScrew("silver");
    public static final DeferredItem<Item> SOUL_INFUSED_SCREW = registerScrew("soul_infused");
    public static final DeferredItem<Item> STAINLESS_STEEL_SCREW = registerScrew("stainless_steel");
    public static final DeferredItem<Item> STEEL_SCREW = registerScrew("steel");
    public static final DeferredItem<Item> TIN_SCREW = registerScrew("tin");
    public static final DeferredItem<Item> TITANIUM_SCREW = registerScrew("titanium");
    public static final DeferredItem<Item> WROUGHT_IRON_SCREW = registerScrew("wrought_iron");
    public static final DeferredItem<Item> ZINC_SCREW = registerScrew("zinc");

    private static DeferredItem<Item> registerIngot(String material) {
        return ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerPlate(String material) {
        return ITEMS.register(material + "_plate", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerNugget(String material) {
        return ITEMS.register(material + "_nugget", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerRod(String material) {
        return ITEMS.register(material + "_rod", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerGear(String material) {
        return ITEMS.register(material + "_gear", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerBolt(String material) {
        return ITEMS.register(material + "_bolt", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerScrew(String material) {
        return ITEMS.register(material + "_screw", () -> new Item(new Item.Properties()));
    }

    public static final DeferredItem<Item> ALUMINIUM_FILE = ITEMS.register("aluminium_file", () -> new FileItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_FILE = ITEMS.register("bronze_file", () -> new FileItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_FILE = ITEMS.register("diamond_file", () -> new FileItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_FILE = ITEMS.register("invar_file", () -> new FileItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_FILE = ITEMS.register("iron_file", () -> new FileItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_FILE = ITEMS.register("stainless_steel_file", () -> new FileItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_FILE = ITEMS.register("steel_file", () -> new FileItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_FILE = ITEMS.register("titanium_file", () -> new FileItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_FILE = ITEMS.register("wrought_iron_file", () -> new FileItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_HAMMER = ITEMS.register("aluminium_hammer", () -> new HammerItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_HAMMER = ITEMS.register("bronze_hammer", () -> new HammerItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_HAMMER = ITEMS.register("diamond_hammer", () -> new HammerItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_HAMMER = ITEMS.register("invar_hammer", () -> new HammerItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new HammerItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_HAMMER = ITEMS.register("stainless_steel_hammer", () -> new HammerItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_HAMMER = ITEMS.register("steel_hammer", () -> new HammerItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_HAMMER = ITEMS.register("titanium_hammer", () -> new HammerItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_HAMMER = ITEMS.register("wrought_iron_hammer", () -> new HammerItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_MORTAR = ITEMS.register("aluminium_mortar", () -> new MortarItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_MORTAR = ITEMS.register("bronze_mortar", () -> new MortarItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_MORTAR = ITEMS.register("diamond_mortar", () -> new MortarItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_MORTAR = ITEMS.register("invar_mortar", () -> new MortarItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_MORTAR = ITEMS.register("iron_mortar", () -> new MortarItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_MORTAR = ITEMS.register("stainless_steel_mortar", () -> new MortarItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_MORTAR = ITEMS.register("steel_mortar", () -> new MortarItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_MORTAR = ITEMS.register("titanium_mortar", () -> new MortarItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_MORTAR = ITEMS.register("wrought_iron_mortar", () -> new MortarItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_SAW = ITEMS.register("aluminium_saw", () -> new SawItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_SAW = ITEMS.register("bronze_saw", () -> new SawItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_SAW = ITEMS.register("diamond_saw", () -> new SawItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_SAW = ITEMS.register("invar_saw", () -> new SawItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_SAW = ITEMS.register("iron_saw", () -> new SawItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_SAW = ITEMS.register("stainless_steel_saw", () -> new SawItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_SAW = ITEMS.register("steel_saw", () -> new SawItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_SAW = ITEMS.register("titanium_saw", () -> new SawItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_SAW = ITEMS.register("wrought_iron_saw", () -> new SawItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_SCREWDRIVER = ITEMS.register("aluminium_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_SCREWDRIVER = ITEMS.register("bronze_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_SCREWDRIVER = ITEMS.register("diamond_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_SCREWDRIVER = ITEMS.register("invar_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_SCREWDRIVER = ITEMS.register("iron_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_SCREWDRIVER = ITEMS.register("stainless_steel_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_SCREWDRIVER = ITEMS.register("steel_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_SCREWDRIVER = ITEMS.register("titanium_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_SCREWDRIVER = ITEMS.register("wrought_iron_screwdriver", () -> new ScrewdriverItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_WIRE_CUTTERS = ITEMS.register("aluminium_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_WIRE_CUTTERS = ITEMS.register("bronze_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_WIRE_CUTTERS = ITEMS.register("diamond_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_WIRE_CUTTERS = ITEMS.register("invar_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_WIRE_CUTTERS = ITEMS.register("iron_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_WIRE_CUTTERS = ITEMS.register("stainless_steel_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_WIRE_CUTTERS = ITEMS.register("steel_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_WIRE_CUTTERS = ITEMS.register("titanium_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_WIRE_CUTTERS = ITEMS.register("wrought_iron_wire_cutters", () -> new WireCutterItem(new Item.Properties().durability(384)));

    public static final DeferredItem<Item> ALUMINIUM_WRENCH = ITEMS.register("aluminium_wrench", () -> new WrenchItem(new Item.Properties().durability(768)));
    public static final DeferredItem<Item> BRONZE_WRENCH = ITEMS.register("bronze_wrench", () -> new WrenchItem(new Item.Properties().durability(192)));
    public static final DeferredItem<Item> DIAMOND_WRENCH = ITEMS.register("diamond_wrench", () -> new WrenchItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> INVAR_WRENCH = ITEMS.register("invar_wrench", () -> new WrenchItem(new Item.Properties().durability(384)));
    public static final DeferredItem<Item> IRON_WRENCH = ITEMS.register("iron_wrench", () -> new WrenchItem(new Item.Properties().durability(256)));
    public static final DeferredItem<Item> STAINLESS_STEEL_WRENCH = ITEMS.register("stainless_steel_wrench", () -> new WrenchItem(new Item.Properties().durability(1024)));
    public static final DeferredItem<Item> STEEL_WRENCH = ITEMS.register("steel_wrench", () -> new WrenchItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> TITANIUM_WRENCH = ITEMS.register("titanium_wrench", () -> new WrenchItem(new Item.Properties().durability(512)));
    public static final DeferredItem<Item> WROUGHT_IRON_WRENCH = ITEMS.register("wrought_iron_wrench", () -> new WrenchItem(new Item.Properties().durability(384)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}