package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.sugar.Local;
import net.frozenblock.lib.item.api.bone_meal.BoneMealApi;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

	@Inject(
			method = "addGrowthParticles",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"
			),
			cancellable = true
	)
	private static void frozenLib$addGrowthParticles(
			LevelAccessor world, BlockPos pos, int count, CallbackInfo info, @Local(ordinal = 0) BlockState blockState
	) {
		BoneMealApi.BoneMealBehavior bonemealBehavior = BoneMealApi.get(blockState.getBlock());
		if (bonemealBehavior != null) {
			BlockPos particlePos = bonemealBehavior.getParticlePos(blockState, pos);
			if (bonemealBehavior.isNeighborSpreader()) {
				ParticleUtils.spawnParticles(world, particlePos, count, 3D, 1D, false, ParticleTypes.HAPPY_VILLAGER);
			} else {
				ParticleUtils.spawnParticleInBlock(world, particlePos, count, ParticleTypes.HAPPY_VILLAGER);
			}
			info.cancel();
		}
	}
}
