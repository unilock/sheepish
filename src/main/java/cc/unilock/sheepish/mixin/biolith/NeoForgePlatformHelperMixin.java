package cc.unilock.sheepish.mixin.biolith;

import com.terraformersmc.biolith.impl.platform.NeoForgePlatformHelper;
import net.neoforged.fml.loading.LoadingModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = NeoForgePlatformHelper.class, remap = false)
public class NeoForgePlatformHelperMixin {
	/**
	 * @author unilock
	 * @reason ModList ==> LoadingModList
	 */
	@Overwrite
	public boolean isModLoaded(String modId) {
		return LoadingModList.get().getModFileById(modId) != null;
	}
}
