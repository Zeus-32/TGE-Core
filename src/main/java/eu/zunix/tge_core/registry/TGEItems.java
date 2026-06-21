package eu.zunix.tge_core.registry;

import java.lang.reflect.Field;
import net.minecraft.world.item.Item;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;

public final class TGEItems {
    public static final DeferredItem<Item> GLASS_DUST =
            register("glass_dust");
    public static final DeferredItem<Item> FLINT_DUST =
            register("flint_dust");
    public static final DeferredItem<Item> CLAY_DUST =
            register("clay_dust");
    public static final DeferredItem<Item> BRICK_DUST =
            register("brick_dust");
    public static final DeferredItem<Item> FIRE_CLAY_DUST =
            register("fire_clay_dust");
    public static final DeferredItem<Item> FIREBRICK =
            register("fire_brick");
    public static final DeferredItem<Item> UNFIRED_CLAY_BRICK =
            register("unfired_clay_brick");
    public static final DeferredItem<Item> BRICK_MOLD =
            register("brick_mold");

    private static DeferredItem<Item> register(String name) {
        return TGEItemRegistry.register(name, Item::new, new Item.Properties());
    }

    private TGEItems() {
    }

    public static void bootstrap() {
        // Triggers static registration before deferred registers are attached.
        TGETools.bootstrap();
    }

    public static void setupCraftingRemainders(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> setCraftingRemainder(BRICK_MOLD.get(), BRICK_MOLD.get()));
    }

    private static void setCraftingRemainder(Item item, Item remainder) {
        try {
            Field field = Item.class.getDeclaredField("craftingRemainingItem");
            field.setAccessible(true);
            field.set(item, remainder);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Failed to configure crafting remainder for brick_mold", exception);
        }
    }
}
