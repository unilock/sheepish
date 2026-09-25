package cc.unilock.sheepish.mixin.anvilcraft;

import dev.dubhe.anvilcraft.mixin.accessor.DelegateBorderChangeListenerAccessor;
import dev.dubhe.anvilcraft.worldgen.OverworldLikeGenerationBootstrap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = OverworldLikeGenerationBootstrap.class, remap = false)
public class OverworldLikeGenerationBootstrapMixin {
	/**
	 * @author unilock
	 * @reason avoid CME
	 */
	@Overwrite
	private static void unlinkOverworldLikeBorder(MinecraftServer server, ServerLevel level) {
		WorldBorder overworldBorder = server.overworld().getWorldBorder();
		List<BorderChangeListener> listeners = new ArrayList<>();
		for (BorderChangeListener listener : ((WorldBorderAccessor) overworldBorder).getListeners()) {
			if (!(listener instanceof DelegateBorderChangeListenerAccessor delegate) || delegate.getWorldBorder() != level.getWorldBorder()) {
				listeners.add(listener);
			}
		}
		((WorldBorderAccessor) overworldBorder).setListeners(listeners);
	}
}
