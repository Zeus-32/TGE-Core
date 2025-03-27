package net.zeus_32.tge_core;

import net.minecraft.util.profiling.jfr.stats.CpuLoadStat;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.block.entity.renderer.ModBlockEntities;
import net.zeus_32.tge_core.item.CreativeModeTabs;
import net.zeus_32.tge_core.item.ModItems;
import net.zeus_32.tge_core.item.ModNonMetalItems;
import net.zeus_32.tge_core.recipe.ModRecipes;
import net.zeus_32.tge_core.screen.ModMenuTypes;
import net.zeus_32.tge_core.screen.custom.CokeOvenMenu;
import net.zeus_32.tge_core.screen.custom.CokeOvenScreen;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


@Mod(TGECore.MODID)
public class TGECore {
    public static final String MODID = "tge_core";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TGECore(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        CreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModNonMetalItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.COKE_OVEN_MENU.get(), CokeOvenScreen::new);
        }
    }
}
