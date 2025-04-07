package net.zeus_32.tge_core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.custom.ModPillarTypeBlock;
import net.zeus_32.tge_core.item.ModItems;
import net.zeus_32.tge_core.worldgen.tree.ModTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TGECore.MODID);

    public static final DeferredBlock<Block> ALUMINIUM_ORE = registerBlock("aluminium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_ALUMINIUM_ORE = registerBlock("deepslate_aluminium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> NICKEL_ORE = registerBlock("nickel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_NICKEL_ORE = registerBlock("deepslate_nickel_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> OSMIUM_ORE = registerBlock("osmium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_OSMIUM_ORE = registerBlock("deepslate_osmium_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ZINC_ORE = registerBlock("zinc_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 5),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));



    public static final DeferredBlock<Block> FIRE_BRICKS = registerBlock("fire_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> STEEL_MACHINE_CASING = registerBlock("steel_machine_casing", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final DeferredBlock<Block> RUBBER_LOG = registerBlock("rubber_log", () -> new ModPillarTypeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> RUBBER_WOOD = registerBlock("rubber_wood", () -> new ModPillarTypeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_LOG = registerBlock("stripped_rubber_log", () -> new ModPillarTypeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));;
    public static final DeferredBlock<Block> STRIPPED_RUBBER_WOOD = registerBlock("stripped_rubber_wood", () -> new ModPillarTypeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> RUBBER_PLANKS = registerBlock("rubber_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 20;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 5;
        }
    });

    public static final DeferredBlock<Block> RUBBER_LEAVES = registerBlock("rubber_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 60;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 30;
        }
    });

    public static final DeferredBlock<Block> RUBBER_SAPLING = registerBlock("rubber_sapling", () -> new SaplingBlock(ModTreeGrower.RUBBER_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}