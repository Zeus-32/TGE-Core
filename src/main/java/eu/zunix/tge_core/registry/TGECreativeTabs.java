package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGECreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TGECore.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS_TAB = TABS.register("materials",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge_core"))
                    .icon(() -> new ItemStack(TGEItems.oreSet(OreMaterial.IRON).ingot().get()))
                    .displayItems((parameters, output) -> TGEItems.displayItems().forEach(item -> output.accept(item.get())))
                    .build());

    private TGECreativeTabs() {
    }

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}
