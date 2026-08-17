package cc.unilock.sheepish.mixin.rgbcraft;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.rgbcraft.RgbCraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = RgbCraft.class, remap = false)
public class RgbCraftMixin {
	@WrapWithCondition(method = "<init>", at = @At(value = "INVOKE", target = "Ldev/rgbcraft/client/PhotosensitivityWarningHandler;register()V"))
	private boolean init$registerPhotosensitivityWarning() {
		return !CONFIG.disableRgbCraftStartupWarning.value();
	}
}
