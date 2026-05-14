package eu.zunix.tge_core.registry;

import java.util.EnumMap;
import java.util.Map;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.block.TGETieredMechanicalMixerBlock;
import eu.zunix.tge_core.content.block.TGETieredMechanicalPressBlock;
import eu.zunix.tge_core.content.block.TGETieredMillstoneBlock;
import eu.zunix.tge_core.content.kinetic.TGEMachineTier;
import eu.zunix.tge_core.content.kinetic.TGEMachineType;
import eu.zunix.tge_core.content.material.OreMaterial;
import eu.zunix.tge_core.content.material.TGEMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGEBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TGECore.MODID);

    private static final Map<OreMaterial, DeferredBlock<Block>> ORES = new EnumMap<>(OreMaterial.class);
    private static final Map<OreMaterial, DeferredBlock<Block>> DEEPSLATE_ORES = new EnumMap<>(OreMaterial.class);
    private static final Map<OreMaterial, DeferredBlock<Block>> NETHER_ORES = new EnumMap<>(OreMaterial.class);
    private static final Map<OreMaterial, DeferredBlock<Block>> END_ORES = new EnumMap<>(OreMaterial.class);
    private static final Map<TGEMachineTier, Map<TGEMachineType, DeferredBlock<? extends Block>>> TIERED_MACHINES =
            new EnumMap<>(TGEMachineTier.class);

    static {
        for (OreMaterial material : TGEMaterials.ACTIVE) {
            if (!material.hasOreForm()) {
                continue;
            }

            ORES.put(material, BLOCKS.register(material.oreId(),
                    () -> new Block(oreProperties(material.baseOreTemplate()))));

            DEEPSLATE_ORES.put(material, BLOCKS.register(material.deepslateOreId(),
                    () -> new Block(oreProperties(material.deepslateOreTemplate()))));

            NETHER_ORES.put(material, BLOCKS.register(material.netherOreId(),
                    () -> new Block(oreProperties(Blocks.NETHER_GOLD_ORE))));

            END_ORES.put(material, BLOCKS.register(material.endOreId(),
                    () -> new Block(oreProperties(Blocks.END_STONE))));
        }

        for (TGEMachineTier tier : TGEMachineTier.values()) {
            Map<TGEMachineType, DeferredBlock<? extends Block>> map = new EnumMap<>(TGEMachineType.class);
            for (TGEMachineType type : TGEMachineType.values()) {
                String id = tier.id() + "_" + type.id();
                map.put(type, BLOCKS.register(id, () -> switch (type) {
                    case MECHANICAL_PRESS -> new TGETieredMechanicalPressBlock(
                            tier,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(4.0F, 10.0F));
                    case MECHANICAL_MIXER -> new TGETieredMechanicalMixerBlock(
                            tier,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(4.0F, 10.0F));
                    case MILLSTONE -> new TGETieredMillstoneBlock(
                            tier,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(4.0F, 10.0F));
                }));
            }
            TIERED_MACHINES.put(tier, Map.copyOf(map));
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

    public static DeferredBlock<Block> netherOre(OreMaterial material) {
        return NETHER_ORES.get(material);
    }

    public static DeferredBlock<Block> endOre(OreMaterial material) {
        return END_ORES.get(material);
    }

    public static boolean hasOre(OreMaterial material) {
        return ORES.containsKey(material)
                && DEEPSLATE_ORES.containsKey(material)
                && NETHER_ORES.containsKey(material)
                && END_ORES.containsKey(material);
    }

    public static DeferredBlock<? extends Block> machine(TGEMachineTier tier, TGEMachineType type) {
        return TIERED_MACHINES.get(tier).get(type);
    }

    private static BlockBehaviour.Properties oreProperties(Block template) {
        // Prevent copying light-level functions that depend on missing state properties
        // (for example vanilla redstone ore requires BooleanProperty.LIT).
        return BlockBehaviour.Properties.ofFullCopy(template).lightLevel(state -> 0);
    }
}
