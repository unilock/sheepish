package cc.unilock.sheepish.compat;

import fuzs.forgeconfigapiport.neoforge.api.forge.v4.ForgeConfigRegistry;
import net.minecraftforge.common.ForgeConfigSpec;
import net.neoforged.fml.config.ModConfig;

import java.lang.invoke.MethodHandles;

public class ExcessiveBuildingCompat {
	public static void init() {
		try {
			ForgeConfigSpec spec = (ForgeConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("net.yirmiri.excessive_building.EBConfig"), "COMMON", ForgeConfigSpec.class).invoke();
			ForgeConfigRegistry.INSTANCE.register("excessive_building", ModConfig.Type.COMMON, spec, "excessive_building-config.toml");
		} catch (Throwable e) {
			throw new RuntimeException("Failed to find / invoke handle for Excessive Building's EBConfig.COMMON", e);
		}
		try {
			ForgeConfigSpec spec = (ForgeConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("net.yirmiri.excessive_building.EBClientConfig"), "CLIENT", ForgeConfigSpec.class).invoke();
			ForgeConfigRegistry.INSTANCE.register("excessive_building", ModConfig.Type.CLIENT, spec, "excessive_building-config-client.toml");
		} catch (Throwable e) {
			throw new RuntimeException("Failed to find / invoke handle for Excessive Building's EBClientConfig.CLIENT", e);
		}
	}
}
