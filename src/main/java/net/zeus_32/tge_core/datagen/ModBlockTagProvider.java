package net.zeus_32.tge_core.datagen;

import com.simibubi.create.AllTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TGECore.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FIRE_BRICKS.get())
                .add(ModBlocks.STEEL_MACHINE_CASING.get())
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get());


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_MACHINE_CASING.get())
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get());


        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.RUBBER_LOG.get())
                .add(ModBlocks.RUBBER_WOOD.get())
                .add(ModBlocks.STRIPPED_RUBBER_LOG.get())
                .add(ModBlocks.STRIPPED_RUBBER_WOOD.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.RUBBER_LEAVES.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.RUBBER_LEAVES.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.RUBBER_LEAVES.get());
        tag(BlockTags.PARROTS_SPAWNABLE_ON)
                .add(ModBlocks.RUBBER_LEAVES.get());
        tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                .add(ModBlocks.RUBBER_LEAVES.get());

        tag(TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath("c", "ores")))
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.ZINC_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get());

        tag(TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath("c", "ores_in_ground/stone")))
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.NICKEL_ORE.get())
                .add(ModBlocks.SILVER_ORE.get())
                .add(ModBlocks.OSMIUM_ORE.get())
                .add(ModBlocks.TIN_ORE.get())
                .add(ModBlocks.ZINC_ORE.get());
        tag(TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath("c", "ores_in_ground/deepslate")))
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_NICKEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_SILVER_ORE.get())
                .add(ModBlocks.DEEPSLATE_OSMIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TIN_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZINC_ORE.get());

        addOreTag("aluminium", ModBlocks.ALUMINIUM_ORE.get(), ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        addOreTag("lead", ModBlocks.LEAD_ORE.get(), ModBlocks.DEEPSLATE_LEAD_ORE.get());
        addOreTag("nickel", ModBlocks.NICKEL_ORE.get(), ModBlocks.DEEPSLATE_NICKEL_ORE.get());
        addOreTag("silver", ModBlocks.SILVER_ORE.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get());
        addOreTag("osmium", ModBlocks.OSMIUM_ORE.get(), ModBlocks.DEEPSLATE_OSMIUM_ORE.get());
        addOreTag("tin", ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get());
        addOreTag("zinc", ModBlocks.ZINC_ORE.get(), ModBlocks.DEEPSLATE_ZINC_ORE.get());

    }

    private void addOreTag(String material, net.minecraft.world.level.block.Block... blocks) {
        tag(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath("c", "ores/" + material)))
                .add(blocks);
    }
}
