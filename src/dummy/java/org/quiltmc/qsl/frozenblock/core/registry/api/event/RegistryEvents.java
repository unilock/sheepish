package org.quiltmc.qsl.frozenblock.core.registry.api.event;

public class RegistryEvents {
	@FunctionalInterface
	public interface EntryAdded<V> {
		void onAdded(RegistryEntryContext<V> context);
	}
}
