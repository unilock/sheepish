package cc.unilock.eternaldeer.mixin.catalogue;

import cc.unilock.eternaldeer.EternalDeer;
import com.mojang.blaze3d.platform.NativeImage;
import com.mrcrayfish.catalogue.platform.NeoForgePlatformHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(value = NeoForgePlatformHelper.class, remap = false)
public class NeoForgePlatformHelperMixin {
	@Inject(method = "loadNativeImage", at = @At(value = "NEW", target = "(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;"), cancellable = true)
	private void loadNativeImage(String modId, String resource, Consumer<NativeImage> consumer, CallbackInfo ci) {
		EternalDeer.LOGGER.error("Catalogue failed to load modId [{}] resource [{}]", modId, resource);
		ci.cancel();
	}
}
