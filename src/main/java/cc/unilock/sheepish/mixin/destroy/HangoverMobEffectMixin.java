package cc.unilock.sheepish.mixin.destroy;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.event.PlayLevelSoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(targets = "petrolpark/mc/destroy/content/product/alcohol/HangoverMobEffect")
@Pseudo
public class HangoverMobEffectMixin {
	@WrapMethod(method = "onPlayerHearsSound")
	private static void onPlayerHearsSound(PlayLevelSoundEvent.AtPosition event, Operation<Void> original) {
		if (event.getLevel() instanceof ServerLevel serverLevel) {
			serverLevel.getServer().execute(() -> original.call(event));
		}
	}
}
