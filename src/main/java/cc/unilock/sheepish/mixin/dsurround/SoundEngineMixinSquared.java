package cc.unilock.sheepish.mixin.dsurround;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.sounds.SoundEngine;
import org.orecruncher.dsurround.lib.logging.IModLog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SoundEngine.class, priority = 1500)
public class SoundEngineMixinSquared {
	@TargetHandler(
			mixin = "org.orecruncher.dsurround.mixins.audio.MixinSoundEngine",
			name = "dsurround$play(Lnet/minecraft/client/resources/sounds/SoundInstance;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V"
	)
	@Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/orecruncher/dsurround/lib/logging/IModLog;warn(Ljava/lang/String;[Ljava/lang/Object;)V"))
	private void play(IModLog instance, String msg, Object[] parms) {
		// NO-OP
	}
}
