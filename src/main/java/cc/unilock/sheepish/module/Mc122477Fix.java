package cc.unilock.sheepish.module;

import cc.unilock.sheepish.Sheepish;
import net.minecraft.client.gui.screens.ChatScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderFrameEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = Sheepish.MOD_ID)
public class Mc122477Fix {
	private boolean freeze = false;
	private byte ticks = 0;

	@SubscribeEvent
	public void opening(final ScreenEvent.Opening event) {
		if (event.getNewScreen() instanceof ChatScreen) {
			freeze = true;
			ticks = 0;
		}
	}

	@SubscribeEvent
	public void closing(final ScreenEvent.Closing event) {
		if (event.getScreen() instanceof ChatScreen) {
			freeze = false;
			ticks = 0;
		}
	}

	@SubscribeEvent
	public void renderFramePost(RenderFrameEvent.Post event) {
		if (freeze) {
			if (++ticks > 2) {
				freeze = false;
			}
		}
	}

	@SubscribeEvent
	public void characterTypedPre(final ScreenEvent.CharacterTyped.Pre event) {
		if (freeze) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void keyPressedPre(final ScreenEvent.KeyPressed.Pre event) {
		if (freeze) {
			event.setCanceled(true);
		}
	}
}
