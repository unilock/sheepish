package cc.unilock.unipack.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import snownee.jade.impl.config.WailaConfig;

import java.util.List;

@Mixin(value = WailaConfig.ConfigGeneral.class, remap = false)
public class WailaConfigGeneralMixin {
	@Redirect(method = "showItemModNameTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"))
	private boolean isEmpty(List instance) {
		return true;
	}
}
