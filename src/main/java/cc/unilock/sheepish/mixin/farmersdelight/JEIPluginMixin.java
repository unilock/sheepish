package cc.unilock.sheepish.mixin.farmersdelight;

import cc.unilock.sheepish.SheepishConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.integration.jei.JEIPlugin;

@Mixin(value = JEIPlugin.class, remap = false)
public class JEIPluginMixin {
	@Inject(method = {
			"registerCategories",
			"registerGuiHandlers",
			"registerRecipeCatalysts",
			"registerRecipeTransferHandlers",
			"registerRecipes"
	}, at = @At("HEAD"), cancellable = true)
	private void cancel(CallbackInfo ci) {
		if (SheepishConfig.CONFIG.disableJeiPlugins.value()) ci.cancel();
	}
}
