package eu.zunix.tge_core.registry;

import net.neoforged.bus.api.IEventBus;

public final class Registries {
    private Registries() {
    }

    public static void register(IEventBus modEventBus) {
        Items.bootstrap();
        ItemRegistry.deferredRegister().register(modEventBus);
        CreativeTabs.deferredRegister().register(modEventBus);
    }
}
