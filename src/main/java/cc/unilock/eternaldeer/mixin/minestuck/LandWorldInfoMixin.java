package cc.unilock.eternaldeer.mixin.minestuck;

import com.mraof.minestuck.world.lands.LandWorldInfo;
import net.minecraft.world.level.storage.ServerLevelData;
import org.ladysnake.cca.api.v3.component.ComponentContainer;
import org.ladysnake.cca.api.v3.component.ComponentProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;

@Mixin(LandWorldInfo.class)
public class LandWorldInfoMixin implements ComponentProvider {
	@Shadow @Final private ServerLevelData wrapped;

	@Nonnull
	@Override
	public ComponentContainer getComponentContainer() {
		return ((ComponentProvider) wrapped).asComponentProvider().getComponentContainer();
	}
}
