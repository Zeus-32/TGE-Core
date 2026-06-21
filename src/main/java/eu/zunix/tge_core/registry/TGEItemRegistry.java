package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.TGECore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGEItemRegistry {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MOD_ID);
    private static final List<DeferredItem<? extends Item>> CREATIVE_TAB_ITEMS = new ArrayList<>();

    private TGEItemRegistry() {
    }

    public static <T extends Item> DeferredItem<T> register(
            String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        DeferredItem<T> item = ITEMS.registerItem(name, factory, properties);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static List<DeferredItem<? extends Item>> creativeTabItems() {
        return Collections.unmodifiableList(CREATIVE_TAB_ITEMS);
    }

    static DeferredRegister.Items deferredRegister() {
        return ITEMS;
    }
}
