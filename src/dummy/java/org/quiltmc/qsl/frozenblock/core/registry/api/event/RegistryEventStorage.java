package org.quiltmc.qsl.frozenblock.core.registry.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.core.MappedRegistry;

public interface RegistryEventStorage<V> {
	Event<RegistryEvents.EntryAdded<V>> frozenLib_quilt$getEntryAddedEvent();

	static <W> RegistryEventStorage<W> as(MappedRegistry<W> registry) {
		throw new AssertionError();
	}
}
