package cc.unilock.sheepish.mixin.c2me;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com/ishland/c2me/fixes/worldgen/threading_issues/common/CheckedThreadLocalRandom")
@Pseudo
public class CheckedThreadLocalRandomMixin {
	@Inject(method = "handleNotOwner", at = @At("HEAD"), cancellable = true)
	private void handleNotOwner(CallbackInfo ci) {
		ci.cancel();
	}
}
