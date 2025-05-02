package io.wispforest.affinity.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FlowingFluid;

public abstract class ArcaneFadeFluid extends FlowingFluid {
	public static final Event<OnTouch> ENTITY_TOUCH_EVENT = EventFactory.createArrayBacked(OnTouch.class, listeners -> entity -> {
		for (var listener : listeners) {
			listener.onTouch(entity);
		}
	});

	public static final Event<OnTouch> ENTITY_TICK_IN_FADE_EVENT = EventFactory.createArrayBacked(OnTouch.class, listeners -> entity -> {
		for (var listener : listeners) {
			listener.onTouch(entity);
		}
	});

	public interface OnTouch {
		void onTouch(Entity entity);
	}
}
