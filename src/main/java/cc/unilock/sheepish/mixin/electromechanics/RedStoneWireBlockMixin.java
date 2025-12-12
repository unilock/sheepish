package cc.unilock.sheepish.mixin.electromechanics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Mixin(RedStoneWireBlock.class)
public class RedStoneWireBlockMixin {
	@Unique
	private static final MethodHandle IS_VALID;
	static {
		try {
			IS_VALID = MethodHandles.privateLookupIn(RedStoneWireBlock.class, MethodHandles.lookup()).findVirtual(RedStoneWireBlock.class, "isValid", MethodType.methodType(boolean.class, new Class[]{BlockGetter.class, Direction.class, BlockPos.class, Direction.class}));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Electromechanic's RedStoneWireBlock#isValid mixin method", e);
		}
	}

	@WrapOperation(method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;canRedstoneConnectTo(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z", ordinal = 0))
	private boolean validConnectionCheckUp(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, Direction direction, Operation<Boolean> original, @Local(argsOnly = true) BlockPos pos){
		if (direction != null) {
			try {
				if(!(boolean) IS_VALID.invoke(this, blockGetter, direction, pos, Direction.UP)) return false;
			} catch (Throwable e) {
				throw new RuntimeException("Failed to invoke handle for Electromechanic's RedStoneWireBlock#isValid mixin method", e);
			}
		}

		return original.call(instance, blockGetter, blockPos, direction);
	}

	@WrapOperation(method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;canRedstoneConnectTo(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z", ordinal = 1))
	private boolean validConnectionCheckDown(BlockState instance, BlockGetter blockGetter, BlockPos blockPos, Direction direction, Operation<Boolean> original, @Local(argsOnly = true) BlockPos pos){
		if (direction != null) {
			try {
				if(!(boolean) IS_VALID.invoke(this, blockGetter, direction, pos, Direction.DOWN)) return false;
			} catch (Throwable e) {
				throw new RuntimeException("Failed to invoke handle for Electromechanic's RedStoneWireBlock#isValid mixin method", e);
			}
		}

		return original.call(instance, blockGetter, blockPos, direction);
	}
}
