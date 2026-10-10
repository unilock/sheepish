package cc.unilock.sheepish.mixin.affinity;

import io.wispforest.affinity.misc.CelestialZoomer;
import io.wispforest.affinity.worldgen.AffinityWorldgen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.EmptyLevelChunk;
import net.neoforged.neoforge.network.handlers.ClientPayloadHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(value = ClientPayloadHandler.class, remap = false)
public class ClientPayloadHandlerMixin {
	@Unique
	private static ResourceKey<Level> affinity$lastWorld = null;

	@ModifyArg(method = "Lnet/neoforged/neoforge/network/handlers/ClientPayloadHandler;handle(Lnet/neoforged/neoforge/network/payload/ClientboundCustomSetTimePayload;Lnet/neoforged/neoforge/network/handling/IPayloadContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;setDayTime(J)V"))
	private static long itIsAlwaysNightyNight(long serverTimeOfDay) {
		final ClientLevel level = Minecraft.getInstance().level;
		final LocalPlayer player = Minecraft.getInstance().player;

		if (level.getChunk(player.blockPosition()) instanceof EmptyLevelChunk) return serverTimeOfDay;
		CelestialZoomer.serverTimeOfDay = serverTimeOfDay;

		if (affinity$isInWispForest(level, player)) {
			final var forestTime = (Math.abs(serverTimeOfDay) / 24000) * -24000 - 18000;

			if (affinity$lastWorld != level.dimension()) {
				level.setDayTime(forestTime);
			}

			CelestialZoomer.enableOffset(forestTime);
		} else if (CelestialZoomer.offsetEnabled()) {
			CelestialZoomer.disableOffset();
		}

		affinity$lastWorld = level.dimension();
		return CelestialZoomer.offsetEnabled() ? level.getDayTime() : serverTimeOfDay;
	}

	@Unique
	private static boolean affinity$isInWispForest(ClientLevel level, LocalPlayer player) {
		return Objects.equals(level.getBiome(player.blockPosition()).unwrapKey().orElse(null), AffinityWorldgen.WISP_FOREST_KEY);
	}
}
