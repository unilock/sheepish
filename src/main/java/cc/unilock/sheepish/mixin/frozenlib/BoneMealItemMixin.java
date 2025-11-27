package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.sugar.Local;
import net.frozenblock.lib.item.api.bone_meal.BoneMealApi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
	@Inject(
			method = "applyBonemeal",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"
			),
			cancellable = true
	)
	private static void frozenLib$runBonemeal(
			ItemStack stack, Level world, BlockPos pos, Player player, CallbackInfoReturnable<Boolean> info, @Local(ordinal = 0) BlockState blockState
	) {
		BoneMealApi.BoneMealBehavior bonemealBehavior = BoneMealApi.get(blockState.getBlock());
		if (bonemealBehavior != null && bonemealBehavior.meetsRequirements(world, pos, blockState)) {
			if (world instanceof ServerLevel serverLevel) {
				if (bonemealBehavior.isBoneMealSuccess(world, world.random, pos, blockState)) {
					bonemealBehavior.performBoneMeal(serverLevel, world.random, pos, blockState);
				}
				stack.shrink(1);
			}
			info.setReturnValue(true);
		}
	}
}
