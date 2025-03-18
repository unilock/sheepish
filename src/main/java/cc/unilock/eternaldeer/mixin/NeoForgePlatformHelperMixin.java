package cc.unilock.eternaldeer.mixin;

import com.terraformersmc.biolith.impl.platform.NeoForgePlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = NeoForgePlatformHelper.class, remap = false)
public class NeoForgePlatformHelperMixin {
	/**
	 * @author unilock
	 * @reason fabric code on neoforge
	 */
	@Overwrite
	public boolean isModLoaded(String modId) {
		return FabricLoader.getInstance().isModLoaded(modId);
	}
}
