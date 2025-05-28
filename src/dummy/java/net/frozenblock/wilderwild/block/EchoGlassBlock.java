package net.frozenblock.wilderwild.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class EchoGlassBlock extends TransparentBlock {
	public EchoGlassBlock(Properties properties) {
		super(properties);
		throw new AssertionError();
	}

	public static boolean canDamage(@NotNull BlockState state) {
		throw new AssertionError();
	}

	public static void setDamagedState(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState blockState) {
		throw new AssertionError();
	}
}
