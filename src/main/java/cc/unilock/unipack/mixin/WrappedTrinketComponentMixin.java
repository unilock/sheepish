package cc.unilock.unipack.mixin;

import dev.emi.trinkets.compat.WrappedTrinketComponent;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WrappedTrinketComponent.class, remap = false)
public class WrappedTrinketComponentMixin {
	@Redirect(method = "lambda$getInventory$0(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;)V"))
	private void silence(Logger instance, String s) {
		// NO-OP
	}

	@Redirect(method = "lambda$getInventory$0(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V"))
	private void silence(Logger instance, String s, Object o) {
		// NO-OP
	}
}
