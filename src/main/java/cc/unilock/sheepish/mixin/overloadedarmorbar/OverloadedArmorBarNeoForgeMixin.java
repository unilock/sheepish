package cc.unilock.sheepish.mixin.overloadedarmorbar;

import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.overpoweredarmorbar.OverloadedArmorBarNeoForge;

@Mixin(value = OverloadedArmorBarNeoForge.class, remap = false)
public class OverloadedArmorBarNeoForgeMixin {
	@Redirect(method = "setup", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/client/event/RegisterGuiLayersEvent;registerAbove(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/gui/LayeredDraw$Layer;)V"))
	private void setup$registerAbove(RegisterGuiLayersEvent instance, ResourceLocation other, ResourceLocation id, LayeredDraw.Layer layer) {
		instance.registerBelow(other, id, layer);
	}
}
