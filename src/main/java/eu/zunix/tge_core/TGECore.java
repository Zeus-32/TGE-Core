package eu.zunix.tge_core;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import eu.zunix.tge_core.registry.TGERegistries;

@Mod(TGECore.MODID)
public class TGECore {
    public static final String MODID = "tge";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TGECore(IEventBus modEventBus, ModContainer modContainer) {
        TGERegistries.register(modEventBus, modContainer);
    }
}
