package cc.unilock.sheepish.mixin.electromechanics;

import com.chyzman.electromechanics.data.SlimeTags;
import com.chyzman.electromechanics.util.Colored;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IBlockStateExtension.class)
public interface IBlockStateExtensionMixin {
	@Shadow private BlockState self() { throw new AssertionError(); }

	@Inject(method = "canStickTo", at = @At("HEAD"), cancellable = true)
	private void canStickTo(BlockState other, CallbackInfoReturnable<Boolean> cir) {
		boolean bl1 = self().is(SlimeTags.Blocks.STICKY_BLOCKS);
		boolean bl2 = other.is(SlimeTags.Blocks.STICKY_BLOCKS);

		if (bl1 && bl2) {
			var block1 = self().getBlock();
			var block2 = other.getBlock();

			if (block1 == block2) {
				cir.setReturnValue(true);

				return;
			}

			var dye1 = block1 instanceof Colored colored1 ? colored1.getColor() : null;
			var dye2 = block2 instanceof Colored colored2 ? colored2.getColor() : null;

			cir.setReturnValue(dye1 == dye2 && !(dye2 == null && isNotHoneySticking(self(), other)));
		} else if (bl1 || bl2) {
			cir.setReturnValue(true);
		}
	}

	@Unique
	private static boolean isNotHoneySticking(BlockState state, BlockState adjacentState){
		return checkAndCompareHoney(state, adjacentState) || checkAndCompareHoney(adjacentState, state);
	}

	@Unique
	private static boolean checkAndCompareHoney(BlockState state, BlockState adjacentState){
		return state.is(Blocks.HONEY_BLOCK) && !adjacentState.is(Blocks.HONEY_BLOCK);
	}
}
