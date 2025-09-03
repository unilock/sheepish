package cc.unilock.sheepish.mixin.yumi_mc_core;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.yumi.mc.core.api.ModContainer;
import dev.yumi.mc.core.impl.YumiFoundationMod;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(value = YumiFoundationMod.class, remap = false)
public class YumiFoundationModMixin {
	@Shadow @Final private static Logger LOGGER;

	/**
	 * @author unilock
	 * @reason avoid StackOverflowError and fail soft
	 */
	@WrapOperation(method = "populateMods", at = @At(value = "INVOKE", target = "Ldev/yumi/mc/core/impl/YumiFoundationMod;populateMods(Ljava/lang/StringBuilder;ILjava/util/Collection;)V"))
	private static void populateMods$populateMods(StringBuilder contained, int depth, Collection<ModContainer> mods, Operation<Void> original, @Local ModContainer mod) {
		if (depth < 9) {
			original.call(contained, depth, mods);
		} else {
			LOGGER.error("Error parsing {}'s contained mods!", mod.getName());
		}
	}
}
