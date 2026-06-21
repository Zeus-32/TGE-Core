package eu.zunix.tge_core.datagen;

import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class DataGenerators {
    private DataGenerators() {
    }

    public static void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new GeneratedResources(event.getGenerator().getPackOutput()));
    }
}
