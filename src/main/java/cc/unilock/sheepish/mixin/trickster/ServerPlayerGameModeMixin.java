package cc.unilock.sheepish.mixin.trickster;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.enjarai.trickster.block.LightBlockEntity;
import dev.enjarai.trickster.block.LightSconceBlock;
import dev.enjarai.trickster.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static dev.enjarai.trickster.block.LightBlock.LIGHT_LEVEL;
import static dev.enjarai.trickster.block.LightSconceBlock.HAS_LIGHT;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {
	@Shadow protected ServerLevel level;

	@WrapOperation(
			method = "destroyBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/server/level/ServerPlayerGameMode;removeBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Z"
			)
	)
	public boolean replaceSconce(
			ServerPlayerGameMode instance, BlockPos blockPos, BlockState destroyedState, boolean b, Operation<Boolean> original, @Local Block block, @Local(ordinal = 0) BlockState blockState
	) {
		if (block instanceof LightSconceBlock && blockState.getValue(HAS_LIGHT) && level.getBlockEntity(blockPos) instanceof LightBlockEntity oldEntity) {
			var colors = oldEntity.getColors();
			var result = original.call(instance, blockPos, destroyedState, b);

			level.setBlock(blockPos, ModBlocks.LIGHT.defaultBlockState().setValue(LIGHT_LEVEL, blockState.getValue(LIGHT_LEVEL)), 0);
			if (level.getBlockEntity(blockPos) instanceof LightBlockEntity newEntity) {
				newEntity.setColors(colors);
			}

			return result;
		}
		return original.call(instance, blockPos, destroyedState, b);
	}
}
