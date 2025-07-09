package dev.spiritstudios.specter.impl.serialization;

import com.google.common.collect.ImmutableMap;
import net.minecraft.network.chat.Component;

public class SpecterSerialization {
	public static final ThreadLocal<ImmutableMap.Builder<String, Component>> TEXT_TRANSLATIONS_BUILDER = ThreadLocal.withInitial(ImmutableMap.Builder::new);
}
