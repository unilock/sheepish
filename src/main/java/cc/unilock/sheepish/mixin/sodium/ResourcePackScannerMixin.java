package cc.unilock.sheepish.mixin.sodium;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.caffeinemc.mods.sodium.client.checks.ResourcePackScanner;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = ResourcePackScanner.class, remap = false)
public class ResourcePackScannerMixin {
	/**
	 * @author unilock
	 * @reason silence warnings
	 */
	@WrapMethod(method = "checkIfCoreShaderLoaded")
	private static void checkIfCoreShaderLoaded$wrap(ResourceManager manager, Operation<Void> original) {
		if (!CONFIG.disableSodiumCoreShaderWarning.value()) original.call(manager);
	}
}
