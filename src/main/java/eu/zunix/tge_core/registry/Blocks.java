package eu.zunix.tge_core.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public final class Blocks {
    private static final List<Entry> ENTRIES = new ArrayList<>();

    public static final Entry FIRE_BRICKS = register(
            "fire_bricks",
            Block::new,
            Block.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.BRICKS));

    private Blocks() {
    }

    public static Entry register(String name, java.util.function.Function<Block.Properties, Block> factory,
            Block.Properties properties) {
        DeferredBlock<Block> block = BlockRegistry.register(name, factory, properties);
        DeferredItem<BlockItem> item = ItemRegistry.register(
                name, itemProperties -> new BlockItem(block.get(), itemProperties), new Item.Properties(), false);
        Entry entry = new Entry(name, block, item);
        ENTRIES.add(entry);
        return entry;
    }

    public static void bootstrap() {
        // Triggers static registration before deferred registers are attached.
    }

    public static List<Entry> entries() {
        return Collections.unmodifiableList(ENTRIES);
    }

    public static List<DeferredItem<BlockItem>> creativeTabItems() {
        return ENTRIES.stream().map(Entry::item).toList();
    }

    public record Entry(String id, DeferredBlock<Block> block, DeferredItem<BlockItem> item) {
    }
}
