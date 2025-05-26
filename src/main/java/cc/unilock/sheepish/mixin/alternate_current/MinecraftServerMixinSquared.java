package cc.unilock.sheepish.mixin.alternate_current;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftServer.class, priority = 1500)
public abstract class MinecraftServerMixinSquared {
	@Shadow public abstract ServerLevel overworld();

	@TargetHandler(
			mixin = "alternate.current.mixin.MinecraftServerMixin",
			name = "alternate_current$save(ZZZLorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;)V"
	)
	@Inject(method = "@MixinSquared:Handler", at = @At("HEAD"))
	private void save(CallbackInfo ci) {
		if (this.overworld() == null) ci.cancel();
	}
}
