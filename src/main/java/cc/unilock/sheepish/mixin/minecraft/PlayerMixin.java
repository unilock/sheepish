package cc.unilock.sheepish.mixin.minecraft;

import cc.unilock.sheepish.module.SheepishPVP;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
		throw new AssertionError();
	}

	@WrapMethod(method = "canEat")
	private boolean canEat$wrap(boolean canAlwaysEat, Operation<Boolean> original) {
		return original.call(canAlwaysEat) || CONFIG.alwaysEat.value();
	}

	@ModifyReturnValue(method = "canHarmPlayer", at = @At("RETURN"))
	private boolean canHarmPlayer(boolean original, Player other) {
		if (!this.is(other)) {
			boolean thisPvpDisabled = !this.getData(SheepishPVP.DATA);
			boolean otherPvpDisabled = !other.getData(SheepishPVP.DATA);

			if (thisPvpDisabled || otherPvpDisabled) {
				return false;
			}
		}

		return original;
	}
}
