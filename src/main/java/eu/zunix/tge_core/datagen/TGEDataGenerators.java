package eu.zunix.tge_core.datagen;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.datagen.provider.TGEBlockTagsProvider;
import eu.zunix.tge_core.datagen.provider.TGEItemModelProvider;
import eu.zunix.tge_core.datagen.provider.TGEItemTagsProvider;
import eu.zunix.tge_core.datagen.provider.TGELangProvider;
import eu.zunix.tge_core.datagen.provider.TGELootTableProvider;
import eu.zunix.tge_core.datagen.provider.TGERecipeProvider;
import eu.zunix.tge_core.datagen.provider.TGEOreBlockStateModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = TGECore.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TGEDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var lookupProvider = event.getLookupProvider();

        if (event.includeClient()) {
            event.createProvider(output -> new TGEOreBlockStateModelProvider(output, event.getExistingFileHelper()));
            event.createProvider(output -> new TGEItemModelProvider(output, event.getExistingFileHelper()));
            event.addProvider(new TGELangProvider(event.getGenerator().getPackOutput()));
        }

        if (event.includeServer()) {
            TGEBlockTagsProvider blockTagsProvider = event.createProvider(
                    output -> new TGEBlockTagsProvider(output, lookupProvider, event.getExistingFileHelper()));
            event.createProvider(output -> new TGEItemTagsProvider(
                    output,
                    lookupProvider,
                    blockTagsProvider.contentsGetter(),
                    event.getExistingFileHelper()));
            event.createProvider(output -> new TGERecipeProvider(output, lookupProvider));
            event.createProvider(output -> new TGELootTableProvider(output, lookupProvider));
        }
    }
}
