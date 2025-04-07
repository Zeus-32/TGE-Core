package net.zeus_32.tge_core.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.item.custom.MoldItem;

public class ModNonMetalItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

    public static final DeferredItem<Item> WOODEN_GEAR = ITEMS.register("wooden_gear", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WATER_WHEEL_PADDLE = ITEMS.register("water_wheel_paddle", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLANT_GOO = ITEMS.register("plant_goo", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLINT_DUST = ITEMS.register("flint_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUARTZ_SAND_DUST = ITEMS.register("quartz_sand_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLASS_DUST = ITEMS.register("glass_dust", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BRICK_MOLD = ITEMS.register("brick_mold", () -> new MoldItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> WET_COKE_BRICK = ITEMS.register("wet_coke_brick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COKE_BRICK = ITEMS.register("coke_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CLAY_DUST = ITEMS.register("clay_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRICK_DUST = ITEMS.register("brick_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_BRICK = ITEMS.register("fire_brick", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRILL_HEAD = ITEMS.register("drill_head", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SAW_BLADE = ITEMS.register("saw_blade", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STICKY_RESIN = ITEMS.register("sticky_resin", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> KINTETIC_MECHANISM = ITEMS.register("kinetic_mechanism", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
