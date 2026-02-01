package cc.unilock.sheepish.mixin.mirthdew_encore;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TickRateManager.class, priority = 1500)
public class TickRateManagerMixinSquared {
	@TargetHandler(
			mixin = "phanastrae.mirthdew_encore.mixin.TickRateManagerMixin",
			name = "mirthdew_encore$freezeEntitiesInDeletingDreamtwirls(Lnet/minecraft/world/entity/Entity;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;)V"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
	private void freezeEntitiesInDeletingDreamtwirls(Entity entity, CallbackInfoReturnable<Boolean> cir, CallbackInfo ci) {
		if (entity == null) ci.cancel();
	}
}
