package net.zeus_32.tge_core.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
        blockWithItem(ModBlocks.COKE_BRICKS);
        blockWithItem(ModBlocks.FIRE_BRICKS);
        customBlockWithItem(ModBlocks.COKE_OVEN, "block/machines/coke_oven");
    }

    private void customBlockWithItem(DeferredBlock<?> deferredBlock, String modelPath) {
        simpleBlockWithItem(deferredBlock.get(), models().getExistingFile(modLoc(modelPath)));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
