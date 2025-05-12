package cc.unilock.eternaldeer.mixin.dsurround;

import org.orecruncher.dsurround.processing.scanner.VillageScanner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = VillageScanner.class, remap = false)
public class VillageScannerMixin {
	// no tick.
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tick(CallbackInfo ci) {
		ci.cancel();
	}
}
