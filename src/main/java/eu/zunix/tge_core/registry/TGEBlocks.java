package eu.zunix.tge_core.registry;

import java.util.EnumMap;
import java.util.Map;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGEBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TGECore.MODID);

    private static final Map<OreMaterial, DeferredBlock<Block>> ORES = new EnumMap<>(OreMaterial.class);
    private static final Map<OreMaterial, DeferredBlock<Block>> DEEPSLATE_ORES = new EnumMap<>(OreMaterial.class);

    static {
        for (OreMaterial material : OreMaterial.values()) {
            ORES.put(material, BLOCKS.register(material.oreId(),
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(material.baseOreTemplate()))));

            DEEPSLATE_ORES.put(material, BLOCKS.register(material.deepslateOreId(),
                    () -> new Block(BlockBehaviour.Properties.ofFullCopy(material.deepslateOreTemplate()))));
        }
    }

    private TGEBlocks() {
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

    public static DeferredBlock<Block> ore(OreMaterial material) {
        return ORES.get(material);
    }

    public static DeferredBlock<Block> deepslateOre(OreMaterial material) {
        return DEEPSLATE_ORES.get(material);
    }
}
