package cc.unilock.eternaldeer.mixin.spectrum;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "de/dafuqs/spectrum/registries/client/SpectrumColorProviders")
@Pseudo
public class SpectrumColorProvidersMixin {
	@Inject(method = "resetToggleableProviders", at = @At("HEAD"), cancellable = true)
	private static void cancelResetToggleableProviders(CallbackInfo ci) {
		ci.cancel();
	}
}
