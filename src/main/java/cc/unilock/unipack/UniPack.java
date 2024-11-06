package cc.unilock.unipack;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(UniPack.MOD_ID)
public class UniPack {
    public static final String MOD_ID = "unipack";
    public static final Logger LOGGER = LogUtils.getLogger();

    public UniPack(IEventBus modEventBus) {
        modEventBus.addListener(this::fmlCommonSetup);
    }

    private void fmlCommonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("UNILOCK WAS HERE");
    }
}
