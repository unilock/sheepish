package net.frozenblock.lib.event.api;

import net.fabricmc.fabric.api.event.Event;

import java.util.function.Function;

public class FrozenEvents {
	public static <T> Event<T> createEnvironmentEvent(Class<? super T> type, Function<T[], T> invokerFactory) {
		throw new AssertionError();
	}
}
