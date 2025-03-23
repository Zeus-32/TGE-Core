package net.zeus_32.tge_core.common;

import net.minecraft.world.item.Item;
import net.zeus_32.tge_core.TGECore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

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

    public static final DeferredItem<Item> BRASS_PLATE = registerPlate("brass");
    public static final DeferredItem<Item> BRONZE_PLATE = registerPlate("bronze");
    public static final DeferredItem<Item> COBALT_PLATE = registerPlate("cobalt");
    public static final DeferredItem<Item> CUPRONICKEL_PLATE = registerPlate("cupronickel");
    public static final DeferredItem<Item> ELECTRUM_PLATE = registerPlate("electrum");
    public static final DeferredItem<Item> ENDERIUM_PLATE = registerPlate("enderium");
    public static final DeferredItem<Item> INVAR_PLATE = registerPlate("invar");
    public static final DeferredItem<Item> IRIDIUM_PLATE = registerPlate("iridium");
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

    // Pomocná metoda pro registraci ingotu
    private static DeferredItem<Item> registerIngot(String material) {
        return ITEMS.register(material + "_ingot", () -> new Item(new Item.Properties()));
    }
    private static DeferredItem<Item> registerPlate(String material) {
        return ITEMS.register(material + "_plate", () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}