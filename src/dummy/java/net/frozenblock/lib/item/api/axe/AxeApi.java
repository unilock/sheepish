package net.frozenblock.lib.item.api.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AxeApi {
	public static AxeBehavior get(Block block) {
		throw new AssertionError();
	}

	public interface AxeBehavior {
		boolean meetsRequirements(LevelReader level, BlockPos pos, Direction direction, BlockState state);
		BlockState getOutputBlockState(BlockState state);
		void onSuccess(Level level, BlockPos pos, Direction direction, BlockState state, BlockState oldState);
	}
}
