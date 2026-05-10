package eu.zunix.tge_core.datagen;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.datagen.provider.TGEOreBlockStateModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = TGECore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TGEDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeClient()) {
            event.createProvider(output -> new TGEOreBlockStateModelProvider(output, event.getExistingFileHelper()));
        }
    }
}
