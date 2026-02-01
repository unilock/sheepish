package cc.unilock.sheepish.mixin.lovely_sparkle_pieces;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.royling.lovelysparklepieces.ModEvents.Legendarys.BCEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BCEvents.class, remap = false)
public class BCEventsMixin {
	@Shadow
	public static boolean hasBlasphemousContract(Player player) {
		throw new AssertionError();
	}

	@WrapWithCondition(method = "onTrySleep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
	private static boolean onTrySleep$displayClientMessage(ServerPlayer instance, Component chatComponent, boolean actionBar) {
		return hasBlasphemousContract(instance);
	}
}
