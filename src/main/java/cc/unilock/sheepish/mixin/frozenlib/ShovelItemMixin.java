package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
	@ModifyExpressionValue(
			method = "useOn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;getToolModifiedState(Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)Lnet/minecraft/world/level/block/state/BlockState;"
			)
	)
	public BlockState frozenlib$removeOtherBehaviorsB(
			BlockState original, @Share(namespace = "net.frozenblock.lib.item.mixin.shovel.ShovelItemMixin", value = "frozenLib$isCustomBehavior") LocalBooleanRef isCustomBehavior
	) {
		if (isCustomBehavior.get()) return null;
		return original;
	}
}
