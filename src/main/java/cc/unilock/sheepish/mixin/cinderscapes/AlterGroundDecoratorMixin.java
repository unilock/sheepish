package cc.unilock.sheepish.mixin.cinderscapes;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.neoforged.neoforge.event.level.AlterGroundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AlterGroundDecorator.class)
public class AlterGroundDecoratorMixin {
	private static final Block NODZOL = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("cinderscapes", "nodzol"));

	@Inject(method = "placeBlockAt(Lnet/minecraft/world/level/levelgen/feature/treedecorators/TreeDecorator$Context;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/event/level/AlterGroundEvent$StateProvider;)V",
			at = @At(
					value = "INVOKE_ASSIGN",
					target = "Lnet/minecraft/core/BlockPos;above(I)Lnet/minecraft/core/BlockPos;",
					shift = At.Shift.AFTER
			),
			cancellable = true
	)
	private void cinderscapes$netherrackConversion(TreeDecorator.Context context, BlockPos pos, AlterGroundEvent.StateProvider eventProvider, CallbackInfo ci, @Local(ordinal = 1) BlockPos above) {
		if (context.level().isStateAtPosition(pos, (state) -> state.is(Blocks.NETHERRACK))) {
			context.setBlock(pos, NODZOL.defaultBlockState());
			ci.cancel();
		}
	}
}
