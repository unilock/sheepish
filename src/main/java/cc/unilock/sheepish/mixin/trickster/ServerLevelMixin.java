package cc.unilock.sheepish.mixin.trickster;

import dev.enjarai.trickster.cca.ModWorldComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
	@Inject(method = "isNaturalSpawningAllowed(Lnet/minecraft/world/level/ChunkPos;)Z", at = @At("HEAD"), cancellable = true)
	private void worldPinTick(ChunkPos pos, CallbackInfoReturnable<Boolean> cir) {
		if (ModWorldComponents.PINNED_CHUNKS.get(this).isPinned(pos)) {
			cir.setReturnValue(true);
		}
	}
}
