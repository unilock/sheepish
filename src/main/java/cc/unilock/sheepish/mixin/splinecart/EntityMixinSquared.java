package cc.unilock.sheepish.mixin.splinecart;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, priority = 1500)
public abstract class EntityMixinSquared {
	@Shadow public abstract Level level();

	@TargetHandler(
			mixin = "io.github.foundationgames.splinecart.mixin.EntityMixin",
			name = "splinecart$getOnTrackIfNecessary"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
	private void splinecart$getOnTrackIfNecessary(CallbackInfo ci) {
		if (level() == null) {
			ci.cancel();
		}
	}
}
