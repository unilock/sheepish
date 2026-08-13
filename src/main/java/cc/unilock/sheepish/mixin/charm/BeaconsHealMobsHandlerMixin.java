package cc.unilock.sheepish.mixin.charm;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(targets = "svenhjol/charm/feature/beacons_heal_mobs/common/Handlers")
@Pseudo
public class BeaconsHealMobsHandlerMixin {
	@WrapMethod(method = "applyBeaconEffects")
	private void applyBeaconEffects(Level level, BlockPos pos, int levels, Holder<MobEffect> primary, Holder<MobEffect> secondary, Operation<Void> original) {
		if (primary != null && secondary != null) {
			original.call(level, pos, levels, primary, secondary);
		}
	}
}
