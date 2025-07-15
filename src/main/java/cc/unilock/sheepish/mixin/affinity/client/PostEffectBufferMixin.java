package cc.unilock.sheepish.mixin.affinity.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "io/wispforest/affinity/client/render/PostEffectBuffer")
@Pseudo
public class PostEffectBufferMixin {
	@Shadow private RenderTarget framebuffer;

	@Inject(method = "lambda$ensureInitialized$0", at = @At("HEAD"), cancellable = true)
	private void lambda$head(CallbackInfo ci) {
		if (this.framebuffer == null) ci.cancel();
	}
}
