package eu.zunix.tge_core.datagen.provider;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.kinetic.TGEMachineTier;
import eu.zunix.tge_core.content.kinetic.TGEMachineType;
import eu.zunix.tge_core.content.material.OreMaterial;
import eu.zunix.tge_core.content.material.TGEMaterials;
import eu.zunix.tge_core.registry.TGEBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TGEOreBlockStateModelProvider extends BlockStateProvider {
    private final ExistingFileHelper existingFileHelper;

    public TGEOreBlockStateModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TGECore.MODID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        for (OreMaterial material : TGEMaterials.ACTIVE) {
            if (!material.hasOreForm() || !TGEBlocks.hasOre(material)) {
                continue;
            }

            ResourceLocation oreTex = TGEOreTextureResolver.oreTexture(material);
            ResourceLocation deepslateTex = TGEOreTextureResolver.deepslateOreTexture(material);
            ResourceLocation netherTex = TGEOreTextureResolver.netherOreTexture(material);
            ResourceLocation endTex = TGEOreTextureResolver.endOreTexture(material);
            existingFileHelper.trackGenerated(oreTex, ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(deepslateTex, ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(netherTex, ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(endTex, ModelProvider.TEXTURE);

            BlockModelBuilder oreModel = models().cubeAll(material.oreId(), oreTex);
            simpleBlockWithItem(TGEBlocks.ore(material).get(), oreModel);

            BlockModelBuilder deepslateModel = models().cubeAll(material.deepslateOreId(), deepslateTex);
            simpleBlockWithItem(TGEBlocks.deepslateOre(material).get(), deepslateModel);

            BlockModelBuilder netherModel = models().cubeAll(material.netherOreId(), netherTex);
            simpleBlockWithItem(TGEBlocks.netherOre(material).get(), netherModel);

            BlockModelBuilder endModel = models().cubeAll(material.endOreId(), endTex);
            simpleBlockWithItem(TGEBlocks.endOre(material).get(), endModel);
        }

        for (TGEMachineTier tier : TGEMachineTier.values()) {
            for (TGEMachineType type : TGEMachineType.values()) {
                String id = tier.id() + "_" + type.id();
                BlockModelBuilder model = machineModel(id, type);
                if (type == TGEMachineType.MECHANICAL_PRESS) {
                    horizontalFacingBlockState(TGEBlocks.machine(tier, type).get(), model);
                } else {
                    simpleBlock(TGEBlocks.machine(tier, type).get(), model);
                }
                simpleBlockItem(TGEBlocks.machine(tier, type).get(), model);
            }
        }
    }

    private BlockModelBuilder machineModel(String id, TGEMachineType type) {
        ResourceLocation parent = switch (type) {
            case MECHANICAL_PRESS -> ResourceLocation.fromNamespaceAndPath("create", "block/mechanical_press/block");
            case MECHANICAL_MIXER -> ResourceLocation.fromNamespaceAndPath("create", "block/mechanical_mixer/block");
            case MILLSTONE -> ResourceLocation.fromNamespaceAndPath("create", "block/millstone/block");
        };

        BlockModelBuilder model = models()
                .getBuilder(id)
                .parent(new ModelFile.UncheckedModelFile(parent.toString()));

        switch (type) {
            case MECHANICAL_PRESS -> {
                ResourceLocation side = ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/machines/mechanical_press_side");
                ResourceLocation top = ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/machines/mechanical_press_top");
                ResourceLocation bottom = ResourceLocation.fromNamespaceAndPath(TGECore.MODID, "block/machines/mechanical_press_bottom");
                model
                        .texture("4", side.toString())
                        .texture("gearbox", side.toString())
                        .texture("gearbox_top", top.toString())
                        .texture("mechanical_press_top", top.toString())
                        .texture("mechanical_press_bottom", bottom.toString())
                        .texture("particle", side.toString());
            }
            case MECHANICAL_MIXER, MILLSTONE -> {
                // Use Create defaults by not overriding textures here.
            }
        }

        return model;
    }


    private void horizontalFacingBlockState(net.minecraft.world.level.block.Block block, BlockModelBuilder model) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int y = switch (facing) {
                case NORTH -> 0;
                case EAST -> 90;
                case SOUTH -> 180;
                case WEST -> 270;
                default -> 0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(y)
                    .build();
        });
    }

}
