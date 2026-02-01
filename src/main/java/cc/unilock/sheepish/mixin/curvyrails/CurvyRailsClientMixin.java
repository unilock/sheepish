package cc.unilock.sheepish.mixin.curvyrails;

import com.froobert.curvyrails.CurvyRailsClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CurvyRailsClient.class, remap = false)
public class CurvyRailsClientMixin {
	@Inject(method = "handleClientLoggedIn", at = @At("HEAD"), cancellable = true)
	private static void handleClientLoggedIn(CallbackInfo ci) {
		ci.cancel();
	}
}
