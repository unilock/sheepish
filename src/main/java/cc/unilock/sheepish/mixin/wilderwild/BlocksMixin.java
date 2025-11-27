package cc.unilock.sheepish.mixin.wilderwild;

import net.frozenblock.wilderwild.registry.WWEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Inject(method = "lambda$static$9", at = @At("HEAD"), cancellable = true)
	private static void wilderWild$allowPenguinSpawnA(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType entityType, CallbackInfoReturnable<Boolean> info) {
		if (entityType == WWEntityTypes.PENGUIN) info.setReturnValue(true);
	}

	@Inject(method = "lambda$static$26", at = @At("HEAD"), cancellable = true)
	private static void wilderWild$allowPenguinSpawnB(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType entityType, CallbackInfoReturnable<Boolean> info) {
		if (entityType == WWEntityTypes.PENGUIN) info.setReturnValue(true);
	}
}
