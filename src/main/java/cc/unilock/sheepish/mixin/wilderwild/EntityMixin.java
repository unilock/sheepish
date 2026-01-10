package cc.unilock.sheepish.mixin.wilderwild;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.frozenblock.wilderwild.entity.impl.InMesogleaInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements InMesogleaInterface {
	@Shadow
	public abstract Level level();

	@WrapOperation(
			method = "getBlockSpeedFactor",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z",
					ordinal = 1
			)
	)
	public boolean wilderWild$isBubbleColumnOrMesogleaColumn(BlockState state, Block block, Operation<Boolean> operation) {
		return operation.call(state, block) || MesogleaBlock.hasBubbleColumn(state);
	}

	@Inject(
			method = "updateFluidOnEyes",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/material/FluidState;getFluidType()Lnet/neoforged/neoforge/fluids/FluidType;"
			)
	)
	public void wilderwild$updateIsInMesolgeaB(
			CallbackInfo info,
			@Local BlockPos eyePos
	) {
		this.wilderWild$setInMesoglea(this.level().getBlockState(eyePos).getBlock() instanceof MesogleaBlock);
	}
}
