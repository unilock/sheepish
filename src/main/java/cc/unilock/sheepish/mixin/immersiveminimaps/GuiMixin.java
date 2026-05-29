package cc.unilock.sheepish.mixin.immersiveminimaps;

import cc.cassian.immersiveminimaps.ModClient;
import cc.cassian.immersiveminimaps.overlay.MinimapHelpers;
import cc.cassian.immersiveminimaps.overlay.MinimapOverlay;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class GuiMixin {
	@ModifyExpressionValue(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiWidth()I"))
	private int renderEffects$guiWidth(int original) {
		if (MinimapHelpers.shouldCancelRender(Minecraft.getInstance()) || !MinimapOverlay.showMinimap || ModClient.CONFIG.style.left_align || ModClient.CONFIG.moved_by_effects) return original;

		return original - ModClient.CONFIG.style.size - 2 * ModClient.CONFIG.style.xOffset;
	}
}
