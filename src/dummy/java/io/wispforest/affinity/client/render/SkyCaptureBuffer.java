package io.wispforest.affinity.client.render;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderType;

public class SkyCaptureBuffer extends RenderType {
	public SkyCaptureBuffer(String name, VertexFormat format, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
		super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
		throw new IllegalStateException("This class should never ever be instantiated");
	}

	public static final RenderType SKY_STENCIL_LAYER = RenderType.create(null, null, null, 0, null);
}
