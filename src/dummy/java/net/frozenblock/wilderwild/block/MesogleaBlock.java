package net.frozenblock.wilderwild.block;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MesogleaBlock extends HalfTransparentBlock {
	public MesogleaBlock(Properties properties) {
		super(properties);
		throw new AssertionError();
	}

	public static boolean hasBubbleColumn(BlockState blockState) {
		throw new AssertionError();
	}

	public ParticleOptions getBubbleParticle() {
		throw new AssertionError();
	}

	public ParticleOptions getSplashParticle() {
		throw new AssertionError();
	}
}
