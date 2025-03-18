package cc.unilock.eternaldeer.mixin.owo;

import com.google.gson.JsonObject;
import io.wispforest.owo.Owo;
import io.wispforest.owo.moddata.ModDataConsumer;
import io.wispforest.owo.moddata.ModDataLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mixin(value = ModDataLoader.class, remap = false)
public abstract class ModDataLoaderMixin {
	@Shadow
	@Final
	private static Path DATA_PATH;

	@Shadow
	private static void tryLoadFilesFrom(Map<ResourceLocation, JsonObject> foundFiles, String namespace, Path targetPath) {
		throw new AssertionError();
	}

	/**
	 * @author unilock
	 * @reason fabric code on neoforge
	 */
	@Overwrite
	public static void load(ModDataConsumer consumer) {
		Map<ResourceLocation, JsonObject> foundFiles = new HashMap<>();

		FabricLoader.getInstance().getAllMods().forEach(modContainer -> {
			final var targetPath = modContainer.getRootPath().resolve(String.format("data/%s/%s", modContainer.getMetadata().getId(), consumer.getDataSubdirectory()));

			tryLoadFilesFrom(foundFiles, modContainer.getMetadata().getId(), targetPath);
		});

		try {
			Files.createDirectories(DATA_PATH);
			Files.list(DATA_PATH).forEach(nsPath -> {
				if (!Files.isDirectory(nsPath)) return;

				var namespace = nsPath.getFileName().toString();
				var targetPath = nsPath.resolve(consumer.getDataSubdirectory());
				if (!Files.exists(targetPath)) return;

				tryLoadFilesFrom(foundFiles, namespace, targetPath);
			});
		} catch (IOException e) {
			Owo.LOGGER.error("### Unable to traverse global data tree ++ Stacktrace below ###", e);
		}

		foundFiles.forEach(consumer::acceptParsedFile);
	}
}
