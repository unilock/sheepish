package fuzs.forgeconfigapiport.fabric.api.neoforge.v4;

import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;

public interface NeoForgeConfigRegistry {
	NeoForgeConfigRegistry INSTANCE = new NeoForgeConfigRegistry() {
		public ModConfig register(String modId, ModConfig.Type type, IConfigSpec spec) {
			return null;
		}
	};

	ModConfig register(String modId, ModConfig.Type type, IConfigSpec spec);
}
