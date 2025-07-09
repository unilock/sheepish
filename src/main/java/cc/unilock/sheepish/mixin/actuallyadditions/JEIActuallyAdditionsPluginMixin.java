package cc.unilock.sheepish.mixin.actuallyadditions;

import cc.unilock.sheepish.SheepishConfig;
import de.ellpeck.actuallyadditions.mod.jei.JEIActuallyAdditionsPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = JEIActuallyAdditionsPlugin.class, remap = false)
public class JEIActuallyAdditionsPluginMixin {
	@Inject(method = {
			"registerCategories",
			"registerGuiHandlers",
			"registerItemSubtypes",
			"registerRecipeCatalysts",
			"registerRecipes"
	}, at = @At("HEAD"), cancellable = true)
	private void cancel(CallbackInfo ci) {
		if (SheepishConfig.CONFIG.disableJeiPlugins.value()) ci.cancel();
	}
}
