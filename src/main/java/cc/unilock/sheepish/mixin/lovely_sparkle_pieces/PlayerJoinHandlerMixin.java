package cc.unilock.sheepish.mixin.lovely_sparkle_pieces;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.royling.lovelysparklepieces.ModEvents.PlayerJoinHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PlayerJoinHandler.class, remap = false)
public class PlayerJoinHandlerMixin {
	@Redirect(method = "onPlayerJoin", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;sendSystemMessage(Lnet/minecraft/network/chat/Component;)V"))
	private static void onPlayerJoin$sendSystemMessage(Player instance, Component component) {
		// NO-OP
	}
}
