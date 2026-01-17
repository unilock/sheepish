package cc.unilock.sheepish.mixin.morecreeps;

import net.graedevs.morecreeps.MoreCreepsEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = MoreCreepsEvents.class, remap = false)
public class MoreCreepsEventsMixin {
	@Inject(method = "playerLoggedIn", at = @At("HEAD"), cancellable = true)
	private static void playerLoggedIn(CallbackInfo ci) {
		if (CONFIG.disableMoreCreepsWelcomeMsg.value()) ci.cancel();
	}
}
