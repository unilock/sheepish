package cc.unilock.unipack.mixin.client;

import cc.unilock.unipack.UniPack;
import com.mojang.blaze3d.platform.NativeImage;
import com.mrcrayfish.catalogue.platform.NeoForgePlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModFileInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(value = NeoForgePlatformHelper.class, remap = false)
public class NeoForgePlatformHelperMixin {
	/**
	 * @author unilock
	 * @reason fail soft
	 */
	@Overwrite
	public void loadNativeImage(String modId, String resource, Consumer<NativeImage> consumer) {
		try {
			NativeImage image = null;
			IModFileInfo info = ModList.get().getModFileById(modId);
			Path path = info.getFile().findResource(resource);
			if (Files.exists(path)) {
				image = NativeImage.read(Files.newInputStream(path));
			}

			Optional.ofNullable(image).ifPresent(consumer);
		} catch (IOException e) {
			UniPack.LOGGER.error("Catalogue failed to load modId [{}] resource [{}]", modId, resource);
//			throw new RuntimeException(e);
		}
	}
}
