package fuzs.forgeconfigapiport.fabric.api.forge.v4;

import net.minecraftforge.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;

public interface ForgeConfigRegistry {
	ForgeConfigRegistry INSTANCE = new ForgeConfigRegistry() {
		@Override
		public ModConfig register(String modId, ModConfig.Type type, IConfigSpec<?> spec, String fileName) {
			return null;
		}
	};

	ModConfig register(String modId, ModConfig.Type type, IConfigSpec<?> spec, String fileName);
}
