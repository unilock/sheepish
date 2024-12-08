package cc.unilock.unipack.mixin.client;

import dev.architectury.registry.client.level.entity.forge.EntityRendererRegistryImpl;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = EntityRendererRegistryImpl.class, remap = false)
public class EntityRendererRegistryImplMixin {
	/**
	 * @author unilock
	 * @reason use fabric code on neoforge
	 */
	@Overwrite
	public static <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
		EntityRendererRegistry.register(type.get(), provider);
	}

	@Inject(method = "<clinit>", at = @At("HEAD"), cancellable = true)
	private static void clinit(CallbackInfo ci) {
		ci.cancel();
	}
}
