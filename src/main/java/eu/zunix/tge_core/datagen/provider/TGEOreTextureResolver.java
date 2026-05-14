package eu.zunix.tge_core.datagen.provider;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.resources.ResourceLocation;

public final class TGEOreTextureResolver {
    private TGEOreTextureResolver() {
    }

    public static ResourceLocation oreTexture(OreMaterial material) {
        return ResourceLocation.fromNamespaceAndPath(
                TGECore.MODID,
                "block/ore/" + material.id() + "_ore");
    }

    public static ResourceLocation deepslateOreTexture(OreMaterial material) {
        return ResourceLocation.fromNamespaceAndPath(
                TGECore.MODID,
                "block/ore/deepslate_" + material.id() + "_ore");
    }

    public static ResourceLocation netherOreTexture(OreMaterial material) {
        return ResourceLocation.fromNamespaceAndPath(
                TGECore.MODID,
                "block/ore/nether_" + material.id() + "_ore");
    }

    public static ResourceLocation endOreTexture(OreMaterial material) {
        return ResourceLocation.fromNamespaceAndPath(
                TGECore.MODID,
                "block/ore/end_" + material.id() + "_ore");
    }
}
