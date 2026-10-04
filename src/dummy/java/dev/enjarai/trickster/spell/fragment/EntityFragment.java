package dev.enjarai.trickster.spell.fragment;

import dev.enjarai.trickster.spell.Fragment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public record EntityFragment(UUID uuid, Component name) implements Fragment {
	public static EntityFragment from(Entity entity) {
		throw new AssertionError();
	}
}
