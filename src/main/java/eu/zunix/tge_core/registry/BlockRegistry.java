package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.TGECore;
import java.util.function.Function;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class BlockRegistry {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TGECore.MOD_ID);

    private BlockRegistry() {
    }

    public static <T extends Block> DeferredBlock<T> register(
            String name, Function<Block.Properties, T> factory, Block.Properties properties) {
        return BLOCKS.registerBlock(name, factory, properties);
    }

    static DeferredRegister.Blocks deferredRegister() {
        return BLOCKS;
    }
}
