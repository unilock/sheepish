package cc.unilock.sheepish.mixin.toomanyrecipeviewers;

import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import dev.nolij.toomanyrecipeviewers.JEIPlugins;
import mezz.jei.api.JeiPlugin;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = JEIPlugins.class, remap = false)
public class JEIPluginsMixin {
	@Inject(method = "getInstances", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	private static void log(CallbackInfoReturnable<List<Class<?>>> cir, @Local(argsOnly = true, ordinal = 0) Class<?> annotationClass, @Local ModFileScanData.AnnotationData annotation) {
		if (CONFIG.tmrvLog.value() && JeiPlugin.class.equals(annotationClass)) {
			Sheepish.LOGGER.info("Found JeiPlugin: "+annotation.memberName());
		}
	}

	@WrapWithCondition(method = "getInstances", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	private static boolean skip(List<Class<?>> instance, @Coerce Object e, @Local(argsOnly = true, ordinal = 0) Class<?> annotationClass, @Local ModFileScanData.AnnotationData annotation) {
		return !(JeiPlugin.class.equals(annotationClass) && CONFIG.tmrvSkip.value().contains(annotation.memberName()));
	}
}
