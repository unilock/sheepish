package io.wispforest.affinity.client.render;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class AbsoluteEnchantmentGlintHandler extends RenderType {
	public AbsoluteEnchantmentGlintHandler(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
		super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
		throw new AssertionError();
	}

	public static void setupCallbacks(Consumer<RenderType> clearAssignment, Consumer<RenderType> assignBuffer) {
		throw new AssertionError();
	}

	public static void prepareGlintColor(ItemStack targetStack) {
		throw new AssertionError();
	}
}
