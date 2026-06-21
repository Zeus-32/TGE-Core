package eu.zunix.tge_core.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class TGEDataGenerators {
    private TGEDataGenerators() {
    }

    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new TGEGeneratedResources(event.getGenerator().getPackOutput()));
    }
}
