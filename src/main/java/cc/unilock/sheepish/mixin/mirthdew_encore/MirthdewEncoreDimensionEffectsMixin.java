package cc.unilock.sheepish.mixin.mirthdew_encore;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import phanastrae.mirthdew_encore.client.render.world.DreamtwirlSkyRenderer;
import phanastrae.mirthdew_encore.client.render.world.MirthdewEncoreDimensionEffects;

@Mixin(value = MirthdewEncoreDimensionEffects.class, remap = false)
public class MirthdewEncoreDimensionEffectsMixin {
	@Shadow
	public DreamtwirlSkyRenderer dreamtwirlSkyRenderer;

	@WrapMethod(method = "close")
	private void close$wrap(Operation<Void> original) {
		if (this.dreamtwirlSkyRenderer != null) original.call();
	}
}
