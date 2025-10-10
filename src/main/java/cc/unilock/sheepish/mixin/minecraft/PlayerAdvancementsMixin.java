package cc.unilock.sheepish.mixin.minecraft;

import cc.unilock.sheepish.Sheepish;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementsMixin {
	@Inject(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementRewards;grant(Lnet/minecraft/server/level/ServerPlayer;)V"))
	private static void award$grant(AdvancementHolder advancement, String criterionKey, CallbackInfoReturnable<Boolean> cir) {
		Sheepish.LOGGER.debug("Attempting to grant advancement \"{}\". criterion \"{}\"", advancement.id(), criterionKey);
	}
}
