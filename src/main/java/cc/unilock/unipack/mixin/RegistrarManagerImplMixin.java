package cc.unilock.unipack.mixin;

import cc.unilock.unipack.mixinsupport.FabricRegistrarManagerImpl;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.forge.RegistrarManagerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = RegistrarManagerImpl.class, remap = false)
public class RegistrarManagerImplMixin {
	/**
	 * @author unilock
	 * @reason use fabric code on neoforge
	 */
	@Overwrite
	public static RegistrarManager.RegistryProvider _get(String modId) {
		return new FabricRegistrarManagerImpl.RegistryProviderImpl(modId);
	}
}
