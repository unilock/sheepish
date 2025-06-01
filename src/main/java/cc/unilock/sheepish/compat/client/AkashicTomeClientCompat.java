package cc.unilock.sheepish.compat.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import vazkii.akashictome.MorphingHandler;

public class AkashicTomeClientCompat {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(InputEvent.InteractionKeyMappingTriggered.class, event -> {
			if (event.isAttack() && Minecraft.getInstance().hitResult != null && Minecraft.getInstance().hitResult.getType() == HitResult.Type.MISS) {
				MorphingHandler.INSTANCE.onPlayerLeftClick(new PlayerInteractEvent.LeftClickEmpty(Minecraft.getInstance().player));
			}
		});
	}
}
