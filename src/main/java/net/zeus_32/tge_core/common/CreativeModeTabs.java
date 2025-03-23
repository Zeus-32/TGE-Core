package net.zeus_32.tge_core.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;

import java.util.function.Supplier;

public class CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TGECore.MODID);

    public static final Supplier<CreativeModeTab> TGE_METALS_TAB = CREATIVE_MODE_TAB.register("tge_metals_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BRASS_INGOT.get()))
                    .title(Component.translatable("creativetab.tge.metals_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BRASS_INGOT);
                        output.accept(ModItems.BRONZE_INGOT);
                        output.accept(ModItems.COBALT_INGOT);
                        output.accept(ModItems.CUPRONICKEL_INGOT);
                        output.accept(ModItems.ELECTRUM_INGOT);
                        output.accept(ModItems.ENDERIUM_INGOT);
                        output.accept(ModItems.INVAR_INGOT);
                        output.accept(ModItems.IRIDIUM_INGOT);
                        output.accept(ModItems.LEAD_INGOT);
                        output.accept(ModItems.LUMIUM_INGOT);
                        output.accept(ModItems.NAQUADAH_INGOT);
                        output.accept(ModItems.NICKEL_INGOT);
                        output.accept(ModItems.OSMIUM_INGOT);
                        output.accept(ModItems.PLATINUM_INGOT);
                        output.accept(ModItems.PLUTONIUM_INGOT);
                        output.accept(ModItems.POLONIUM_INGOT);
                        output.accept(ModItems.RED_ALLOY_INGOT);
                        output.accept(ModItems.SILVER_INGOT);
                        output.accept(ModItems.SOUL_INFUSED_INGOT);
                        output.accept(ModItems.STAINLESS_STEEL_INGOT);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.TIN_INGOT);
                        output.accept(ModItems.TITANIUM_INGOT);
                        output.accept(ModItems.URANIUM_INGOT);
                        output.accept(ModItems.WROUGHT_IRON_INGOT);
                        output.accept(ModItems.ZINC_INGOT);

                        output.accept(ModItems.BRASS_PLATE);
                        output.accept(ModItems.BRONZE_PLATE);
                        output.accept(ModItems.COBALT_PLATE);
                        output.accept(ModItems.CUPRONICKEL_PLATE);
                        output.accept(ModItems.ELECTRUM_PLATE);
                        output.accept(ModItems.ENDERIUM_PLATE);
                        output.accept(ModItems.INVAR_PLATE);
                        output.accept(ModItems.IRIDIUM_PLATE);
                        output.accept(ModItems.LEAD_PLATE);
                        output.accept(ModItems.LUMIUM_PLATE);
                        output.accept(ModItems.NAQUADAH_PLATE);
                        output.accept(ModItems.NICKEL_PLATE);
                        output.accept(ModItems.OSMIUM_PLATE);
                        output.accept(ModItems.PLATINUM_PLATE);
                        output.accept(ModItems.RED_ALLOY_PLATE);
                        output.accept(ModItems.SILVER_PLATE);
                        output.accept(ModItems.SOUL_INFUSED_PLATE);
                        output.accept(ModItems.STAINLESS_STEEL_PLATE);
                        output.accept(ModItems.STEEL_PLATE);
                        output.accept(ModItems.TIN_PLATE);
                        output.accept(ModItems.TITANIUM_PLATE);
                        output.accept(ModItems.WROUGHT_IRON_PLATE);
                        output.accept(ModItems.ZINC_PLATE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}