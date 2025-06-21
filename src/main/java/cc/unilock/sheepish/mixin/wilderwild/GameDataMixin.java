package cc.unilock.sheepish.mixin.wilderwild;

import net.frozenblock.lib.event.api.RegistryFreezeEvents;
import net.neoforged.neoforge.registries.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameData.class, remap = false)
public class GameDataMixin {
	@Inject(method = "freezeData", at = @At("HEAD"))
	private static void freezeData$head(CallbackInfo ci) {
		RegistryFreezeEvents.START_REGISTRY_FREEZE.invoker().onStartRegistryFreeze(null, true);
	}

	@Inject(method = "freezeData", at = @At("TAIL"))
	private static void freezeData$tail(CallbackInfo ci) {
		RegistryFreezeEvents.END_REGISTRY_FREEZE.invoker().onEndRegistryFreeze(null, true);
	}
}
