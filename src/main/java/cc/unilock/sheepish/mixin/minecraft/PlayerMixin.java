package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(Player.class)
public class PlayerMixin {
	@WrapMethod(method = "canEat")
	private boolean canEat$wrap(boolean canAlwaysEat, Operation<Boolean> original) {
		return original.call(canAlwaysEat) || CONFIG.alwaysEat.value();
	}
}
