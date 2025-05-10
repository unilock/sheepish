package cc.unilock.eternaldeer.mixin.affinity.client;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import io.wispforest.affinity.client.render.AbsoluteEnchantmentGlintHandler;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderBuffers.class)
public abstract class RenderBuffersMixin {
	@Shadow private static void put(Object2ObjectLinkedOpenHashMap<RenderType, ByteBufferBuilder> mapBuilders, RenderType renderType) { throw new AssertionError(); }

	@Inject(method = "lambda$new$1", at = @At("TAIL"))
	private void insertAffinityLayers(Object2ObjectLinkedOpenHashMap<RenderType, ByteBufferBuilder> builderStorage, CallbackInfo callbackInfo) {
		AbsoluteEnchantmentGlintHandler.setupCallbacks(
				builderStorage::remove,
				renderLayer -> put(builderStorage, renderLayer)
		);
	}
}
