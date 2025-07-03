package cc.unilock.sheepish.mixin.blueprint;

import com.teamabnormals.blueprint.client.screen.splash.BlueprintSplashManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = BlueprintSplashManager.class, remap = false)
public class BlueprintSplashManagerMixin {
	@Inject(method = "onRegisterClientReloadListeners", at = @At("HEAD"), cancellable = true)
	private static void onRegisterClientReloadListeners(CallbackInfo ci) {
		if (CONFIG.disableSplashes.value()) ci.cancel();
	}
}
