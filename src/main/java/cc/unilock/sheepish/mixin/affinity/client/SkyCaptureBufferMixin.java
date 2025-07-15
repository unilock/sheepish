package cc.unilock.sheepish.mixin.affinity.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "io/wispforest/affinity/client/render/SkyCaptureBuffer")
@Pseudo
public class SkyCaptureBufferMixin {
	@Shadow
	private static RenderTarget skyCapture;

	@Inject(method = "lambda$static$3", at = @At("HEAD"), cancellable = true)
	private static void lambda$head(CallbackInfo ci) {
		if (skyCapture == null) ci.cancel();
	}
}
