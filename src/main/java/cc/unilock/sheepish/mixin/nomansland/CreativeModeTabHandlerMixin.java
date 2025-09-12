package cc.unilock.sheepish.mixin.nomansland;

import com.farcr.nomansland.common.event.CreativeModeTabHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CreativeModeTabHandler.class, remap = false)
public class CreativeModeTabHandlerMixin {
	@ModifyExpressionValue(method = "onBuildCreativeModeTabContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/flag/FeatureFlagSet;contains(Lnet/minecraft/world/flag/FeatureFlag;)Z"))
	private boolean onBuildCreativeModeTabContents$contains(boolean original) {
		return original || ModList.get().isLoaded("ported");
	}
}
