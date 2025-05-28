package net.frozenblock.lib.worldgen.biome.api;

import net.frozenblock.lib.worldgen.biome.impl.FrozenGrassColorModifier;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FrozenGrassColorModifiers {
	public static Optional<FrozenGrassColorModifier> getGrassColorModifier(@NotNull ResourceLocation id) {
		throw new AssertionError();
	}
}
