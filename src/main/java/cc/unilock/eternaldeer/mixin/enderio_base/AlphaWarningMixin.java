package cc.unilock.eternaldeer.mixin.enderio_base;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com/enderio/base/common/AlphaWarning")
@Pseudo
public class AlphaWarningMixin {
	@Inject(method = "playerJoin(Lnet/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerLoggedInEvent;)V", at = @At("HEAD"), cancellable = true)
	private static void playerJoin(CallbackInfo ci) {
		ci.cancel();
	}
}
