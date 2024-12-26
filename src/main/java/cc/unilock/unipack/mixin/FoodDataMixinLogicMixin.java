package cc.unilock.unipack.mixin;

import dev.sterner.witchery.mixin_logic.FoodDataMixinLogic;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodDataMixinLogic.class)
public class FoodDataMixinLogicMixin {
	@Inject(method = "onAdd", at = @At("HEAD"), cancellable = true)
	private void onAdd(Player instance, int foodLevel, float saturationLevel, CallbackInfo ci) {
		if (instance == null) ci.cancel();
	}
}
