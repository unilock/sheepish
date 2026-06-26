package cc.unilock.sheepish.mixin.yigd;

import com.b1n_ry.yigd.compat.InvModCompat;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = InvModCompat.class, remap = false)
public interface InvModCompatMixin {
	@WrapOperation(method = "reloadModCompat", at = @At(value = "INVOKE", target = "Lnet/neoforged/fml/ModList;isLoaded(Ljava/lang/String;)Z", ordinal = 2))
	private static boolean reloadModCompat$isLoaded(ModList instance, String modTarget, Operation<Boolean> original) {
		return original.call(instance, modTarget) && !CONFIG.forceYigdCuriosCompat.value();
	}
}
