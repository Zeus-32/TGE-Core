package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.Config;
import eu.zunix.tge_core.worldgen.TGEOreGenerationEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

public final class TGERegistries {
    private TGERegistries() {
    }

    public static void register(IEventBus modEventBus, ModContainer modContainer) {
        TGEBlocks.register(modEventBus);
        TGEItems.register(modEventBus);
        TGECreativeTabs.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.register(new TGEOreGenerationEvents());
    }
}
