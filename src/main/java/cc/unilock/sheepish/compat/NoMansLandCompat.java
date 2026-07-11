package cc.unilock.sheepish.compat;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NoMansLandCompat {
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_APPLE_01 = key("oak_apple_01");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_APPLE_05 = key("oak_apple_05");

	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_APPLE_01 = key("fancy_oak_apple_01");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_APPLE_05 = key("fancy_oak_apple_05");

	private static ResourceKey<ConfiguredFeature<?, ?>> key(String path) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath("nomansland", path));
	}
}
