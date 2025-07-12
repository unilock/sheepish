package cc.unilock.sheepish.mixin.toughnessbar;

import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.toughnessbar.ToughnessBar;

@Mixin(value = ToughnessBar.class, remap = false)
public class ToughnessBarMixin {
	/**
	 * @author unilock
	 * @reason registerBelow(CHAT) -> registerAbove(FOOD_LEVEL)
	 */
	@Redirect(method = "setup", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/client/event/RegisterGuiLayersEvent;registerBelow(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/gui/LayeredDraw$Layer;)V"))
	private void setup$registerBelow(RegisterGuiLayersEvent instance, ResourceLocation other, ResourceLocation id, LayeredDraw.Layer layer) {
		instance.registerAbove(VanillaGuiLayers.FOOD_LEVEL, id, layer);
	}
}
