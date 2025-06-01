package cc.unilock.sheepish.compat;

import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.lang.invoke.MethodHandles;

public class AnsharCompat {
	public static void init() {
		try {
			ModConfigSpec spec = (ModConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("com.lgmrszd.anshar.config.ServerConfig"), "CONFIG_SPEC", ModConfigSpec.class).invoke();
			ModList.get().getModContainerById("anshar").orElseThrow().registerConfig(ModConfig.Type.SERVER, spec);
		} catch (Throwable e) {
			throw new RuntimeException("Failed to find / invoke handle for Anshar's ServerConfig.CONFIG_SPEC", e);
		}
	}
}
