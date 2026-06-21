package eu.zunix.tge_core;

import eu.zunix.tge_core.datagen.DataGenerators;
import eu.zunix.tge_core.registry.Items;
import eu.zunix.tge_core.registry.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TGECore.MOD_ID)
public final class TGECore {
    public static final String MOD_ID = "tge";

    public TGECore(IEventBus modEventBus) {
        Registries.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(Items::setupCraftingRemainders);
    }
}
