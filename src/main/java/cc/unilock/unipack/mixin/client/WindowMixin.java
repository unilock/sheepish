package cc.unilock.unipack.mixin.client;

import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
	@Inject(method = "defaultErrorCallback", at = @At("HEAD"), cancellable = true)
	private void defaultErrorCallback(CallbackInfo ci) {
		ci.cancel();
	}
}
