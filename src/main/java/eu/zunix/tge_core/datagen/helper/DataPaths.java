package eu.zunix.tge_core.datagen.helper;

import java.nio.file.Path;
import net.minecraft.data.PackOutput;

public final class DataPaths {
    private DataPaths() {
    }

    public static Path client(PackOutput output, String relative) {
        return output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve("tge").resolve(relative);
    }

    public static Path server(PackOutput output, String namespace, String relative) {
        return output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(namespace).resolve(relative);
    }
}
