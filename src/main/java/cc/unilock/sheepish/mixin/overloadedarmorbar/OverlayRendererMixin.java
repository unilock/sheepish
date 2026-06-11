package cc.unilock.sheepish.mixin.overloadedarmorbar;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import tfar.overloadedarmorbar.overlay.OverlayRenderer;
import tfar.overloadedarmorbar.platform.services.IPlatformHelper;

@Mixin(value = OverlayRenderer.class, remap = false)
public class OverlayRendererMixin {
	@WrapWithCondition(method = "renderArmorBar", at = @At(value = "INVOKE", target = "Ltfar/overloadedarmorbar/platform/services/IPlatformHelper;offsetLeftHeight(Lnet/minecraft/client/gui/Gui;I)V"))
	private static boolean renderArmorBar$offsetLeftHeight(IPlatformHelper instance, Gui gui, int offset, @Local(name = "currentArmorValue") int currentArmorValue) {
		return currentArmorValue > 0;
	}
}
