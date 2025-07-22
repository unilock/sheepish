package cc.unilock.sheepish.mixin.surveyor;

import com.bawnorton.mixinsquared.TargetHandler;
import folk.sisby.surveyor.ServerSummary;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftServer.class, priority = 1500)
public abstract class MinecraftServerMixinSquared {
	@Shadow(remap = false)
	private ServerSummary surveyor$summary;

	@TargetHandler(
			mixin = "folk.sisby.surveyor.mixin.MixinMinecraftServer",
			name = "saveSummary(ZZZLorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;)V"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
	private void saveSummary(CallbackInfo ci) {
		if (this.surveyor$summary == null) ci.cancel();
	}
}
