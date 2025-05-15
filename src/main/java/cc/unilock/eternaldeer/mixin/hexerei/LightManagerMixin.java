package cc.unilock.eternaldeer.mixin.hexerei;

import net.joefoxe.hexerei.light.LightManager;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LightManager.class, remap = false)
public class LightManagerMixin {
	/**
	 * @author unilock
	 * @reason disable dynamic light; @Overwrite to fail hard
	 */
	@Overwrite
	public static boolean shouldUpdateDynamicLight() {
		return false;
	}

	@Redirect(method = "toggleLightsAndConfig", at = @At(value = "INVOKE", target = "Lnet/neoforged/fml/ModList;isLoaded(Ljava/lang/String;)Z"))
	private static boolean toggleLightsAndConfig$isLoaded(ModList instance, String modTarget) {
		return true;
	}
}
