package net.zeus_32.tge_core.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TGECore.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.FIRE_BRICKS);
        blockWithItem(ModBlocks.STEEL_MACHINE_CASING);

        blockWithItem(ModBlocks.ALUMINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);
        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockWithItem(ModBlocks.NICKEL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_NICKEL_ORE);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockWithItem(ModBlocks.OSMIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_OSMIUM_ORE);
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TIN_ORE);
        blockWithItem(ModBlocks.ZINC_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ZINC_ORE);

        logBlock(((RotatedPillarBlock) ModBlocks.RUBBER_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.RUBBER_WOOD.get()), blockTexture(ModBlocks.RUBBER_LOG.get()), blockTexture(ModBlocks.RUBBER_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_WOOD.get()), blockTexture(ModBlocks.STRIPPED_RUBBER_LOG.get()), blockTexture(ModBlocks.STRIPPED_RUBBER_LOG.get()));

        blockItem(ModBlocks.RUBBER_LOG);
        blockItem(ModBlocks.RUBBER_WOOD);
        blockItem(ModBlocks.STRIPPED_RUBBER_LOG);
        blockItem(ModBlocks.STRIPPED_RUBBER_WOOD);

        blockWithItem(ModBlocks.RUBBER_PLANKS);

        saplingBlock(ModBlocks.RUBBER_SAPLING);
        leavesBlock(ModBlocks.RUBBER_LEAVES);
    }
    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tge_core:block/" + deferredBlock.getId().getPath()));
    }
}