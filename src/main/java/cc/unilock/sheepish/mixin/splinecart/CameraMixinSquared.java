package cc.unilock.sheepish.mixin.splinecart;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Camera.class, priority = 1500)
public class CameraMixinSquared {
	@Shadow private Entity entity;

	@TargetHandler(
			mixin = "io.github.foundationgames.splinecart.mixin.client.CameraMixin",
			name = "splinecart$updateCamPosWhileRiding"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
	private void splinecart$updateCamPosWhileRiding(CallbackInfo ci) {
		if (this.entity == null) ci.cancel();
	}

	@TargetHandler(
			mixin = "io.github.foundationgames.splinecart.mixin.client.CameraMixin",
			name = "splinecart$updateCamRotationWhileRiding"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
	private void splinecart$updateCamRotationWhileRiding(CallbackInfo ci) {
		if (this.entity == null) ci.cancel();
	}
}
