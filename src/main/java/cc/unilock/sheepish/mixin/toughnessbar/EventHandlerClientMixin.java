package cc.unilock.sheepish.mixin.toughnessbar;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.toughnessbar.EventHandlerClient;

@Mixin(value = EventHandlerClient.class, remap = false)
public class EventHandlerClientMixin {
	@Shadow
	@Final
	private static Minecraft mc;

	@ModifyVariable(method = "render", at = @At("LOAD"), name = "rightHeight")
	private static int modifyRightHeight(int original) {
		return mc.gui.rightHeight;
	}

	@Inject(method = "render", at = @At("TAIL"))
	private static void render$tail(CallbackInfo ci) {
		if (mc.getCameraEntity() instanceof Player player) {
			if (!player.isCreative() && !player.isSpectator()) {
				if (Mth.floor(player.getAttributeValue(Attributes.ARMOR_TOUGHNESS)) > 0) {
					mc.gui.rightHeight += 10;
				}
			}
		}
	}
}
