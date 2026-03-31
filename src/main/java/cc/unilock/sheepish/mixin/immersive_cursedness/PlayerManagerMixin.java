package cc.unilock.sheepish.mixin.immersive_cursedness;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.bundle.PacketAndPayloadAcceptor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(targets = "nl/theepicblock/immersive_cursedness/PlayerManager")
@Pseudo
public class PlayerManagerMixin  {
	@Redirect(method = "processRealEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerEntity;sendPairingData(Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V"))
	private void processRealEntities$sendPackets(ServerEntity entity, ServerPlayer player, Consumer<Packet<? super ClientGamePacketListener>> consumer) {
		entity.sendPairingData(player, new PacketAndPayloadAcceptor<>(consumer));
	}

	@Redirect(method = "lambda$purgeCache$15", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerEntity;sendPairingData(Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V"))
	private void lambda$purgeCache$15$sendPackets(ServerEntity entity, ServerPlayer player, Consumer<Packet<? super ClientGamePacketListener>> consumer) {
		entity.sendPairingData(player, new PacketAndPayloadAcceptor<>(consumer));
	}
}
