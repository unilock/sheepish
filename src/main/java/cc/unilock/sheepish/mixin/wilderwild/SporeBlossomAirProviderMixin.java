package cc.unilock.sheepish.mixin.wilderwild;

import net.frozenblock.wilderwild.particle.impl.WilderDripSuspendedParticleInterface;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SuspendedParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SuspendedParticle.SporeBlossomAirProvider.class)
public class SporeBlossomAirProviderMixin {
	@Inject(method = "createParticle*", at = @At("RETURN"))
	public void wilderWild$createParticle(CallbackInfoReturnable<Particle> info) {
		if (info.getReturnValue() instanceof WilderDripSuspendedParticleInterface suspendedParticle) suspendedParticle.wilderWild$setUsesWind(true);
	}
}
