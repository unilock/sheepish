package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.resources.SplashManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = SplashManager.class)
public class SplashManagerMixin {
	@WrapOperation(method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z", remap = false))
	private boolean prepare$equals(String left, Object right, Operation<Boolean> original) {
		return !CONFIG.disableSplashes.value() && original.call(left, right);
	}
}
