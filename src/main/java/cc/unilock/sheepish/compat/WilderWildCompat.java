package cc.unilock.sheepish.compat;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class WilderWildCompat {
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK = key("oak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BEES_0004 = key("oak_bees_0004");

	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK = key("fancy_oak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_BEES = key("fancy_oak_bees");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_BEES_0004 = key("fancy_oak_bees_0004");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OAK_BEES_025 = key("fancy_oak_bees_025");

	private static ResourceKey<ConfiguredFeature<?, ?>> key(String path) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath("wilderwild", path));
	}
}
