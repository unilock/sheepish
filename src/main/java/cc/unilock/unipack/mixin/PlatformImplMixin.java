package cc.unilock.unipack.mixin;

import dev.architectury.platform.forge.PlatformImpl;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PlatformImpl.class, remap = false)
public class PlatformImplMixin {
	/**
	 * @author unilock
	 * @reason use fabric code on neoforge
	 */
	@Overwrite
	public static boolean isModLoaded(String id) {
		return FabricLoader.getInstance().isModLoaded(id);
	}
}
