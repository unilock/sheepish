package cc.unilock.sheepish.compat.client;

import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class CeruleanClientCompat {
	private static final ResourceKey<Level> DREAMSCAPE = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("cerulean", "dreamscape"));
	private static final ResourceKey<Level> SKIES = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("cerulean", "skies"));

	public static void init() {
		DimensionRenderingRegistry.registerCloudRenderer(DREAMSCAPE, ctx -> {});
		DimensionRenderingRegistry.registerCloudRenderer(SKIES, ctx -> {});
		DimensionRenderingRegistry.registerWeatherRenderer(DREAMSCAPE, ctx -> {});
		DimensionRenderingRegistry.registerWeatherRenderer(SKIES, ctx -> {});
	}
}
