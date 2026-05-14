package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.kinetic.TGEMachineTier;
import eu.zunix.tge_core.content.kinetic.TGEMachineType;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TGECore.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS_TAB = TABS.register("materials",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.materials"))
                    .icon(() -> {
                        try {
                            var set = TGEItems.oreSet(OreMaterial.IRON);
                            if (set == null || set.ingot() == null) return new ItemStack(Items.AIR);
                            return new ItemStack(set.ingot().get());
                        } catch (Exception e) {
                            return new ItemStack(Items.AIR);
                        }
                    })
                    .displayItems((parameters, output) -> {
                        for (var deferred : TGEItems.materialItems()) {
                            try {
                                var it = deferred.get();
                                if (it != null) output.accept(new ItemStack(it));
                            } catch (Exception ignored) {
                            }
                        }
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS_TAB = TABS.register("blocks",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.blocks"))
                    .icon(() -> {
                        try {
                            var block = TGEBlocks.ore(OreMaterial.DIAMOND);
                            return new ItemStack(block == null ? Items.AIR : block.get());
                        } catch (Exception e) {
                            return new ItemStack(Items.AIR);
                        }
                    })
                    .displayItems((parameters, output) -> {
                        for (var deferred : TGEItems.blockItems()) {
                            try {
                                var it = deferred.get();
                                if (it != null) output.accept(new ItemStack(it));
                            } catch (Exception ignored) {
                            }
                        }
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS_TAB = TABS.register("tools",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.tools"))
                    .icon(() -> {
                        try {
                            return new ItemStack(TGEItems.PROSPECTOR.get());
                        } catch (Exception e) {
                            return new ItemStack(Items.AIR);
                        }
                    })
                    .displayItems((parameters, output) -> {
                        for (var deferred : TGEItems.toolItems()) {
                            try {
                                var it = deferred.get();
                                if (it != null) output.accept(new ItemStack(it));
                            } catch (Exception ignored) {
                            }
                        }
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MACHINES_TAB = TABS.register("machinery",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.machinery"))
                    .icon(() -> {
                        try {
                            var set = TGEItems.machineSet(TGEMachineTier.ANDESITE);
                            if (set == null || set.item(TGEMachineType.MECHANICAL_PRESS) == null) return new ItemStack(Items.AIR);
                            return new ItemStack(set.item(TGEMachineType.MECHANICAL_PRESS).get());
                        } catch (Exception e) {
                            return new ItemStack(Items.AIR);
                        }
                    })
                    .displayItems((parameters, output) -> {
                        for (var deferred : TGEItems.machineItems()) {
                            try {
                                var it = deferred.get();
                                if (it != null) output.accept(new ItemStack(it));
                            } catch (Exception ignored) {
                            }
                        }
                    })
                    .build());

    private TGECreativeTabs() {
    }

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}