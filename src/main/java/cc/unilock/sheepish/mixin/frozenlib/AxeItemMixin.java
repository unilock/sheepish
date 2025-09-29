package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.lib.item.api.axe.AxeApi;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
	@WrapOperation(
			method = "useOn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/AxeItem;evaluateNewBlockState(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/context/UseOnContext;)Ljava/util/Optional;",
					ordinal = 0
			)
	)
	public Optional<BlockState> frozenlib$runAxeBehavior(
			AxeItem instance,
			Level world,
			BlockPos pos,
			Player player,
			BlockState state,
			UseOnContext context,
			Operation<Optional<BlockState>> original
	) {
		BlockState blockState = world.getBlockState(pos);
		Direction direction = context.getClickedFace();
		AxeApi.AxeBehavior axeBehavior = AxeApi.get(blockState.getBlock());
		if (axeBehavior != null && axeBehavior.meetsRequirements(world, pos, direction, state)) {
			BlockState outputState = axeBehavior.getOutputBlockState(state);
			if (outputState != null) {
				axeBehavior.onSuccess(world, pos, direction, outputState, state);
				return Optional.of(outputState);
			}
		}

		return original.call(instance, world, pos, player, state, context);
	}
}
