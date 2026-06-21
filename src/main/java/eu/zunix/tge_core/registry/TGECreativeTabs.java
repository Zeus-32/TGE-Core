package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.TGECore;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGECreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TGECore.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = TABS.register(
            "main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.main"))
                    .icon(() -> TGEItems.FIREBRICK.get().getDefaultInstance())
                    .displayItems((parameters, output) ->
                            TGEItemRegistry.creativeTabItems().forEach(item -> output.accept(item.get())))
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TOOLS = TABS.register(
            "tools",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tge.tools"))
                    .icon(() -> TGETools.entries().getFirst().item().get().getDefaultInstance())
                    .displayItems((parameters, output) ->
                            TGETools.entries().forEach(tool -> output.accept(tool.item().get())))
                    .build());

    private TGECreativeTabs() {
    }

    static DeferredRegister<CreativeModeTab> deferredRegister() {
        return TABS;
    }
}
