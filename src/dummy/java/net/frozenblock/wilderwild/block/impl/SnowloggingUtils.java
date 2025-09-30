package net.frozenblock.wilderwild.block.impl;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SnowloggingUtils {
	public static final IntegerProperty SNOW_LAYERS = IntegerProperty.create(null, 0, 0);

	public static boolean isSnowlogged(BlockState state) {
		throw new AssertionError();
	}

	public static BlockState getSnowEquivalent(BlockState state) {
		throw new AssertionError();
	}
}
