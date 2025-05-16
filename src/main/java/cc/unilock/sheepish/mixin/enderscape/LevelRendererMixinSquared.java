package cc.unilock.sheepish.mixin.enderscape;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LevelRenderer.class, priority = 1500)
public class LevelRendererMixinSquared {
	@Shadow(remap = false)
	@Mutable
	private static @Final Minecraft client;

	@TargetHandler(
			mixin = "net.bunten.enderscape.client.mixin.LevelRendererMixin",
			name = "renderEndSky(Lcom/mojang/blaze3d/vertex/PoseStack;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"))
	private void renderEndSky(CallbackInfo ci) {
		if (client == null) {
			client = Minecraft.getInstance();
		}
	}
}
