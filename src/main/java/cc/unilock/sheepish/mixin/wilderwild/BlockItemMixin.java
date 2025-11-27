package cc.unilock.sheepish.mixin.wilderwild;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.wilderwild.block.impl.SnowloggingUtils;
import net.frozenblock.wilderwild.config.WWBlockConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
	@WrapOperation(
			method = "place",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;getSoundType(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/level/block/SoundType;"
			)
	)
	public SoundType wilderWild$place(BlockState instance, LevelReader levelReader, BlockPos blockPos, Entity entity, Operation<SoundType> original) {
		return (WWBlockConfig.canSnowlog() && (instance.hasProperty(SnowloggingUtils.SNOW_LAYERS) && instance.getValue(SnowloggingUtils.SNOW_LAYERS) > 0))
				? original.call(SnowloggingUtils.getSnowEquivalent(instance), levelReader, blockPos, entity)
				: original.call(instance, levelReader, blockPos, entity);
	}

	@Inject(method = "getPlaceSound(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/sounds/SoundEvent;", at = @At("HEAD"), cancellable = true)
	public void wilderWild$getPlaceSound(BlockState state, Level world, BlockPos pos, Player entity, CallbackInfoReturnable<SoundEvent> info) {
		if (!WWBlockConfig.canSnowlog()) return;
		if (state.hasProperty(SnowloggingUtils.SNOW_LAYERS) && state.getValue(SnowloggingUtils.SNOW_LAYERS) > 0) {
			info.setReturnValue(SnowloggingUtils.getSnowEquivalent(state).getSoundType().getPlaceSound());
		}
	}
}
