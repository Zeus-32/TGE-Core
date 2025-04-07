package net.zeus_32.tge_core.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        try {
            registerMetalBlocks();

            registerWoodBlocks();

            registerSpecialBlocks();

            registerOre();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate loot tables", e);
        }
    }

    private void registerMetalBlocks() {
        dropSelf(ModBlocks.STEEL_MACHINE_CASING.get());
        dropSelf(ModBlocks.FIRE_BRICKS.get());
    }

    private void registerWoodBlocks() {
        // Rubber wood
        dropSelf(ModBlocks.RUBBER_LOG.get());
        dropSelf(ModBlocks.RUBBER_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_RUBBER_LOG.get());
        dropSelf(ModBlocks.STRIPPED_RUBBER_WOOD.get());
        dropSelf(ModBlocks.RUBBER_PLANKS.get());
        dropSelf(ModBlocks.RUBBER_SAPLING.get());

        // Listy s náhodným dropem sazenice
        add(ModBlocks.RUBBER_LEAVES.get(),
                createLeavesDrops(ModBlocks.RUBBER_LEAVES.get(), ModBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    private void registerSpecialBlocks() {
        // Přidejte zde speciální bloky s vlastními loot tabulkami
    }

    private void registerOre() {
        add(ModBlocks.ALUMINIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));
        add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));

        add(ModBlocks.LEAD_ORE.get(),
                block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        add(ModBlocks.DEEPSLATE_LEAD_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_LEAD_ORE.get(), ModItems.RAW_LEAD.get()));

        add(ModBlocks.NICKEL_ORE.get(),
                block -> createOreDrop(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        add(ModBlocks.DEEPSLATE_NICKEL_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));

        add(ModBlocks.SILVER_ORE.get(),
                block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        add(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));

        add(ModBlocks.OSMIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.OSMIUM_ORE.get(), ModItems.RAW_OSMIUM.get()));
        add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_OSMIUM_ORE.get(), ModItems.RAW_OSMIUM.get()));

        add(ModBlocks.TIN_ORE.get(),
                block -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        add(ModBlocks.DEEPSLATE_TIN_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));

        add(ModBlocks.ZINC_ORE.get(),
                block -> createOreDrop(ModBlocks.ZINC_ORE.get(), ModItems.RAW_ZINC.get()));
        add(ModBlocks.DEEPSLATE_ZINC_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ZINC_ORE.get(), ModItems.RAW_ZINC.get()));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
