package cc.unilock.unipack.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import snownee.jade.gui.WailaConfigScreen;

import java.util.List;

@Mixin(value = WailaConfigScreen.class, remap = false)
public class WailaConfigScreenMixin {
	@Redirect(method = "createOptions", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"))
	private boolean isEmpty(List instance) {
		return true;
	}
}
