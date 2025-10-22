package cc.unilock.sheepish.mixin.zeta;

import cc.unilock.sheepish.Sheepish;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(targets = "org/violetmoon/zetaimplforge/client/ForgeZetaClient")
@Pseudo
public class ForgeZetaClientMixin {
	/**
	 * @author unilock
	 * @reason slightly less hacky
	 */
	@Overwrite
	public RegistryAccess hackilyGetCurrentClientLevelRegistryAccess() {
		return Sheepish.getRegistryAccess();
	}
}
