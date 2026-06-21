package eu.zunix.tge_core.registry;

import net.neoforged.bus.api.IEventBus;

public final class TGERegistries {
    private TGERegistries() {
    }

    public static void register(IEventBus modEventBus) {
        TGEItems.bootstrap();
        TGEItemRegistry.deferredRegister().register(modEventBus);
        TGECreativeTabs.deferredRegister().register(modEventBus);
    }
}
