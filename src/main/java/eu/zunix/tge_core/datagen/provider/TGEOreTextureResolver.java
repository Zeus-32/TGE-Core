package eu.zunix.tge_core.datagen.provider;

import java.util.Map;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public final class TGEOreTextureResolver {
    private static final Map<String, String> BASE_ALIAS = Map.of(
            "aluminium", "aluminum");

    private TGEOreTextureResolver() {
    }

    public static String oreTexture(OreMaterial material, ExistingFileHelper existingFileHelper) {
        String candidate = alias(material.id()) + "_ore";
        if (exists(existingFileHelper, candidate)) {
            return candidate;
        }
        return fallbackOre(material);
    }

    public static String deepslateOreTexture(OreMaterial material, ExistingFileHelper existingFileHelper) {
        String candidate = "deepslate_" + alias(material.id()) + "_ore";
        if (exists(existingFileHelper, candidate)) {
            return candidate;
        }
        return fallbackDeepslate(material);
    }

    private static String alias(String id) {
        return BASE_ALIAS.getOrDefault(id, id);
    }

    private static boolean exists(ExistingFileHelper existingFileHelper, String textureName) {
        ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath(
                TGECore.MODID,
                "textures/block/ore/" + textureName + ".png");
        return existingFileHelper.exists(textureLocation, PackType.CLIENT_RESOURCES);
    }

    private static String fallbackOre(OreMaterial material) {
        return switch (material) {
            case GOLD, ELECTRUM -> "gold_ore";
            case COPPER -> "copper_ore";
            default -> "iron_ore";
        };
    }

    private static String fallbackDeepslate(OreMaterial material) {
        return switch (material) {
            case GOLD, ELECTRUM -> "deepslate_gold_ore";
            case COPPER -> "deepslate_copper_ore";
            default -> "deepslate_iron_ore";
        };
    }
}
