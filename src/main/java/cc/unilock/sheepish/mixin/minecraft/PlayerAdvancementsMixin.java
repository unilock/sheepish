package cc.unilock.sheepish.mixin.minecraft;

import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementsMixin {
//	@WrapOperation(method = "award", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementRewards;grant(Lnet/minecraft/server/level/ServerPlayer;)V"))
	@WrapMethod(method = "award")
	private static boolean award$wrap(AdvancementHolder advancement, String criterionKey, Operation<Boolean> original) {
		try {
			return original.call(advancement, criterionKey);
		} catch (AbstractMethodError ignored) {
			Sheepish.LOGGER.error("Failed to grant advancement \"{}\", criterion \"{}\"", advancement.id(), criterionKey);
			return false;
		}
	}
}
