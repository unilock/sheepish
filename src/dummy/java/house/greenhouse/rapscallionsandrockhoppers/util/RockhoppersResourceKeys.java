package house.greenhouse.rapscallionsandrockhoppers.util;

import house.greenhouse.rapscallionsandrockhoppers.entity.PenguinVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class RockhoppersResourceKeys {
	public static final ResourceKey<Registry<PenguinVariant>> PENGUIN_VARIANT = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("rapscallionsandrockhoppers","penguin_variant"));
}
