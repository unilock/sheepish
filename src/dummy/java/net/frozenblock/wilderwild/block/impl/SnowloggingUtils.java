package net.frozenblock.wilderwild.block.impl;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class SnowloggingUtils {
	public static final IntegerProperty SNOW_LAYERS = IntegerProperty.create(null, 0, 0);

	public static boolean isSnowlogged(@Nullable BlockState state) {
		throw new AssertionError();
	}
}
