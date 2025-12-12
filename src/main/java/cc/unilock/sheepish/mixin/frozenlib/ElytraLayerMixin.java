package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.frozenblock.lib.cape.client.impl.AbstractClientPlayerCapeInterface;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin<T extends LivingEntity> {
	@ModifyExpressionValue(
			method = "render*",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/resources/PlayerSkin;capeTexture()Lnet/minecraft/resources/ResourceLocation;",
					ordinal = 0
			)
	)
	public ResourceLocation frozenLib$captureAndChangeCapeLocation(
			ResourceLocation resourceLocation,
			@Local(argsOnly = true) T livingEntity,
			@Share("frozenLib$newCapeTexture") LocalRef<ResourceLocation> newCapeTexture
	) {
		if (livingEntity instanceof AbstractClientPlayerCapeInterface capeInterface) {
			ResourceLocation capeTexture = capeInterface.frozenLib$getCape();
			if (capeTexture != null) {
				newCapeTexture.set(capeTexture);
				return capeTexture;
			}
		}
		return resourceLocation;
	}

	@ModifyExpressionValue(
			method = "render*",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/resources/PlayerSkin;capeTexture()Lnet/minecraft/resources/ResourceLocation;",
					ordinal = 1
			)
	)
	public ResourceLocation frozenLib$renderNewCape(
			ResourceLocation resourceLocation,
			@Share("frozenLib$newCapeTexture") LocalRef<ResourceLocation> newCapeTexture
	) {
		ResourceLocation capeTexture = newCapeTexture.get();
		if (capeTexture != null) return capeTexture;
		return resourceLocation;
	}
}
