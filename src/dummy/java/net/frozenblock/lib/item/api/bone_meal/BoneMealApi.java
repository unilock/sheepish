package net.frozenblock.lib.item.api.bone_meal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BoneMealApi {
	public static BoneMealApi.BoneMealBehavior get(Block block) {
		throw new AssertionError();
	}

	public interface BoneMealBehavior {
		boolean meetsRequirements(LevelReader level, BlockPos pos, BlockState state);

		default boolean isBoneMealSuccess(LevelReader level, RandomSource random, BlockPos pos, BlockState state) {
			return true;
		}

		void performBoneMeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state);

		default BlockPos getParticlePos(BlockState state, BlockPos pos) {
			return pos;
		}

		default boolean isNeighborSpreader() {
			return false;
		}
	}
}
