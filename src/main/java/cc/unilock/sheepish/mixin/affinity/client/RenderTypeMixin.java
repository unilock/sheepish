package cc.unilock.sheepish.mixin.affinity.client;

import com.google.common.collect.ImmutableList;
import io.wispforest.affinity.client.render.SkyCaptureBuffer;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RenderType.class)
public class RenderTypeMixin {
	@Shadow
	@Mutable
	public static @Final ImmutableList<RenderType> CHUNK_BUFFER_LAYERS;

	static {
		CHUNK_BUFFER_LAYERS = ImmutableList.<RenderType>builder().addAll(CHUNK_BUFFER_LAYERS).add(SkyCaptureBuffer.SKY_STENCIL_LAYER).build();
	}
}
