package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.frozenblock.wilderwild.block.EchoGlassBlock;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.frozenblock.wilderwild.block.impl.SnowloggingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {
	@Shadow protected ServerLevel level;
	@Shadow @Final protected ServerPlayer player;

	@Shadow public abstract GameType getGameModeForPlayer();

	@WrapOperation(
			method = "destroyBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/server/level/ServerPlayerGameMode;removeBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Z"
			)
	)
	public boolean wilderWild$destroyBlockB(
			ServerPlayerGameMode instance, BlockPos pos, BlockState state, boolean canHarvest, Operation<Boolean> original, @Local(ordinal = 1) BlockState destroyedState
	) {
		if (SnowloggingUtils.isSnowlogged(destroyedState)) {
			return original.call(instance, pos, destroyedState.setValue(SnowloggingUtils.SNOW_LAYERS, 0), canHarvest);
		}
		if (destroyedState.getBlock() instanceof MesogleaBlock) {
			return original.call(instance, pos, Blocks.AIR.defaultBlockState(), canHarvest);
		}
		if (destroyedState.getBlock() instanceof EchoGlassBlock && EchoGlassBlock.canDamage(destroyedState) && !this.getGameModeForPlayer().isCreative()) {
			var silkTouch = this.level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
			if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, this.player.getMainHandItem()) < 1) {
				EchoGlassBlock.setDamagedState(this.level, pos, destroyedState);
				return original.call(instance, pos, destroyedState, canHarvest);
			}
		}
		return original.call(instance, pos, state, canHarvest);
	}
}
