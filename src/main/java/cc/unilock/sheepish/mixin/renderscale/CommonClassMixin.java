package cc.unilock.sheepish.mixin.renderscale;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.zelo.renderscale.CommonClass;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CommonClass.class, remap = false)
public class CommonClassMixin {
	@WrapWithCondition(method = "onResolutionChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;resize(II)V"))
	private boolean onResolutionChanged$resize(LevelRenderer instance, int width, int height) {
		return instance != null;
	}
}
