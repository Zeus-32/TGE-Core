package eu.zunix.tge_core;

import eu.zunix.tge_core.datagen.TGEDataGenerators;
import eu.zunix.tge_core.registry.TGEItems;
import eu.zunix.tge_core.registry.TGERegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TGECore.MOD_ID)
public final class TGECore {
    public static final String MOD_ID = "tge";

    public TGECore(IEventBus modEventBus) {
        TGERegistries.register(modEventBus);
        modEventBus.addListener(TGEDataGenerators::gatherData);
        modEventBus.addListener(TGEItems::setupCraftingRemainders);
    }
}
