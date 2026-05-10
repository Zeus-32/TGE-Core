package eu.zunix.tge_core.datagen.provider;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import eu.zunix.tge_core.registry.TGEBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TGEOreBlockStateModelProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public TGEOreBlockStateModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TGECore.MODID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        for (OreMaterial material : OreMaterial.values()) {
            String oreTex = TGEOreTextureResolver.oreTexture(material, existingFileHelper);
            String deepslateTex = TGEOreTextureResolver.deepslateOreTexture(material, existingFileHelper);

            BlockModelBuilder oreModel = models().cubeAll(
                    material.oreId(),
                    ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/ore/" + oreTex));
            simpleBlockWithItem(TGEBlocks.ore(material).get(), oreModel);

            BlockModelBuilder deepslateModel = models().cubeAll(
                    material.deepslateOreId(),
                    ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/ore/" + deepslateTex));
            simpleBlockWithItem(TGEBlocks.deepslateOre(material).get(), deepslateModel);
        }
    }
}
