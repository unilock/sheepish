package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.wilderwild.block.EchoGlassBlock;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.frozenblock.wilderwild.block.impl.SnowloggingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MultiPlayerGameMode.class)
public abstract class MultiPlayerGameModeMixin {
	@Shadow @Final private Minecraft minecraft;

	@Shadow public abstract GameType getPlayerMode();

	@WrapOperation(
			method = "destroyBlock",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/Block;destroy(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
			)
	)
	public void wilderWild$destroyBlockB(
			Block instance, LevelAccessor level, BlockPos pos, BlockState destroyedState, Operation<Void> original
	) {
		if (SnowloggingUtils.isSnowlogged(destroyedState)) {
			original.call(instance, level, pos, destroyedState.setValue(SnowloggingUtils.SNOW_LAYERS, 0));
		}
		if (destroyedState.getBlock() instanceof MesogleaBlock) {
			original.call(instance, level, pos, Blocks.AIR.defaultBlockState());
		}
		if (destroyedState.getBlock() instanceof EchoGlassBlock && EchoGlassBlock.canDamage(destroyedState) && !this.getPlayerMode().isCreative()) {
			var silkTouch = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);
			if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, this.minecraft.player.getMainHandItem()) < 1) EchoGlassBlock.setDamagedState(this.minecraft.player.level(), pos, destroyedState);
			original.call(instance, level, pos, destroyedState);
			return;
		}
		original.call(instance, level, pos, destroyedState);
	}
}
