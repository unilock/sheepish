package cc.unilock.sheepish.mixin.yumi_mc_core;

import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.yumi.mc.core.api.ModContainer;
import dev.yumi.mc.core.impl.YumiFoundationMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(value = YumiFoundationMod.class, remap = false)
public class YumiFoundationModMixin {
	/**
	 * @author unilock
	 * @reason catch StackOverflowError and fail soft
	 */
	@WrapOperation(method = "populateMods", at = @At(value = "INVOKE", target = "Ldev/yumi/mc/core/impl/YumiFoundationMod;populateMods(Ljava/lang/StringBuilder;ILjava/util/Collection;)V"))
	private static void populateMods$populateMods(StringBuilder contained, int depth, Collection<ModContainer> mods, Operation<Void> original, @Local ModContainer mod) {
		try {
			original.call(contained, depth, mods);
		} catch (StackOverflowError e) {
			Sheepish.LOGGER.error("Error parsing "+mod.getName()+"'s contained mods!");
		}
	}
}
