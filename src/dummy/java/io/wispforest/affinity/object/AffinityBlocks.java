package io.wispforest.affinity.object;

import io.wispforest.affinity.misc.ArcaneFadeFluid;
import net.minecraft.world.level.material.FlowingFluid;

public class AffinityBlocks {
	public static class Fluids {
		public static final FlowingFluid ARCANE_FADE = new ArcaneFadeFluid.Still();
		public static final FlowingFluid ARCANE_FADE_FLOWING = new ArcaneFadeFluid.Flowing();
	}
}
