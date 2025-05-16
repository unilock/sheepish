package cc.unilock.sheepish.mixin.spectrum;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.spectrum.render.HudRenderers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
	@Shadow @Final private Minecraft minecraft;

	@Inject(method = "renderHealthLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
	private void spectrum$renderAzureDikeBar(GuiGraphics guiGraphics, CallbackInfo ci, @Local Player cameraPlayer, @Local(index = 8) int x, @Local(index = 10) int y, @Local(index = 13) int heartRows, @Local(index = 14) int rowHeight) {
		minecraft.getProfiler().push("spectrum:azure");
		HudRenderers.renderAzureDike(guiGraphics, cameraPlayer, x, y - (heartRows - 1) * rowHeight - 10);
		minecraft.getProfiler().pop();
	}
}
