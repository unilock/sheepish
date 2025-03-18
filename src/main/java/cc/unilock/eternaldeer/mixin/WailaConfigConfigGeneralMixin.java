package cc.unilock.eternaldeer.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import snownee.jade.impl.config.WailaConfig;

import java.util.Collection;
import java.util.List;

@Mixin(value = WailaConfig.ConfigGeneral.class, remap = false)
public class WailaConfigConfigGeneralMixin {
	@Redirect(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z"))
	private static boolean init$addAll(List instance, Collection es) {
		return false;
	}
}
