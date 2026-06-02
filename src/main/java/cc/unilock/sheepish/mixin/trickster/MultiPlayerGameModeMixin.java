package cc.unilock.sheepish.mixin.trickster;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.enjarai.trickster.block.LightBlockEntity;
import dev.enjarai.trickster.block.LightSconceBlock;
import dev.enjarai.trickster.block.ModBlocks;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static dev.enjarai.trickster.block.LightBlock.LIGHT_LEVEL;
import static dev.enjarai.trickster.block.LightSconceBlock.HAS_LIGHT;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {
	@WrapOperation(
			method = "destroyBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;onDestroyedByPlayer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;ZLnet/minecraft/world/level/material/FluidState;)Z"
			)
	)
	public boolean replaceSconce(
			BlockState blockState, Level world, BlockPos blockPos, Player player, boolean b, FluidState fluidState, Operation<Boolean> original, @Local Block block
	) {
		if (block instanceof LightSconceBlock && blockState.getValue(HAS_LIGHT) && world.getBlockEntity(blockPos) instanceof LightBlockEntity oldEntity) {
			var colors = oldEntity.getColors();
			var result = original.call(blockState, world, blockPos, player, b, fluidState);

			world.setBlock(blockPos, ModBlocks.LIGHT.defaultBlockState().setValue(LIGHT_LEVEL, blockState.getValue(LIGHT_LEVEL)), 0);
			if (world.getBlockEntity(blockPos) instanceof LightBlockEntity newEntity) {
				newEntity.setColors(colors);
			}

			return result;
		}

		return original.call(blockState, world, blockPos, player, b, fluidState);
	}
}
