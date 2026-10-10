package io.wispforest.affinity.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class AffinityWorldgen {
	public static final ResourceKey<Biome> WISP_FOREST_KEY = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("affinity", "wisp_forest"));
}
