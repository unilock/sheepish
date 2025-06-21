package net.frozenblock.lib.event.api;

import net.fabricmc.fabric.api.event.Event;
import net.frozenblock.lib.entrypoint.api.CommonEventEntrypoint;
import net.minecraft.core.Registry;

import javax.annotation.Nullable;

public class RegistryFreezeEvents {
	public static final Event<StartRegistryFreeze> START_REGISTRY_FREEZE = FrozenEvents.createEnvironmentEvent(StartRegistryFreeze.class,
			callbacks -> (registry, allRegistries) -> {
				for (var callback : callbacks) {
					callback.onStartRegistryFreeze(registry, allRegistries);
				}
			});

	public static final Event<EndRegistryFreeze> END_REGISTRY_FREEZE = FrozenEvents.createEnvironmentEvent(EndRegistryFreeze.class, 
			callbacks -> (registry, allRegistries) -> {
				for (var callback : callbacks) {
					callback.onEndRegistryFreeze(registry, allRegistries);
				}
			});

	@FunctionalInterface
	public interface StartRegistryFreeze extends CommonEventEntrypoint {
		void onStartRegistryFreeze(@Nullable Registry<?> registry, boolean allRegistries);
	}

	@FunctionalInterface
	public interface EndRegistryFreeze extends CommonEventEntrypoint {
		void onEndRegistryFreeze(@Nullable Registry<?> registry, boolean allRegistries);
	}
}
