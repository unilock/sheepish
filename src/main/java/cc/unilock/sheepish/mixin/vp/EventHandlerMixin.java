package cc.unilock.sheepish.mixin.vp;

import com.pyding.vp.events.EventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EventHandler.class, remap = false)
public class EventHandlerMixin {
	@Redirect(method = "loginIn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sendSystemMessage(Lnet/minecraft/network/chat/Component;)V"))
	private static void loginIn$sendSystemMessage(Player instance, Component component) {
		// NO-OP
	}
}
