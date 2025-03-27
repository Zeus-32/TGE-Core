package net.zeus_32.tge_core.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.item.custom.MoldItem;

public class ModNonMetalItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

    public static final DeferredItem<Item> FLINT_DUST = ITEMS.register("flint_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUARTZ_SAND_DUST = ITEMS.register("quartz_sand_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GLASS_DUST = ITEMS.register("glass_dust", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BRICK_MOLD = ITEMS.register("brick_mold", () -> new MoldItem(new Item.Properties().durability(1)));
    public static final DeferredItem<Item> WET_COKE_BRICK = ITEMS.register("wet_coke_brick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COKE_BRICK = ITEMS.register("coke_brick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COAL_COKE = ITEMS.register("coke_coal", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CLAY_DUST = ITEMS.register("clay_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BRICK_DUST = ITEMS.register("brick_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FIRE_BRICK = ITEMS.register("fire_brick", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
