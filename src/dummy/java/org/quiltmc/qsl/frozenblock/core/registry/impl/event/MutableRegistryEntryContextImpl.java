package org.quiltmc.qsl.frozenblock.core.registry.impl.event;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.qsl.frozenblock.core.registry.api.event.RegistryEntryContext;

public class MutableRegistryEntryContextImpl<V> implements RegistryEntryContext<V> {
	public MutableRegistryEntryContextImpl(Registry<V> registry) {
		throw new AssertionError();
	}

	public void set(ResourceLocation id, V entry, int rawId) {
		throw new AssertionError();
	}
}
