package cc.unilock.sheepish.mixin.lavender;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.wispforest.lavender.client.LavenderBookScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.ClientHooks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientHooks.class)
public class ClientHooksMixin {
	@WrapMethod(method = "getGuiFarPlane")
	private static float getGuiFarPlane(Operation<Float> original) {
		if (Minecraft.getInstance().screen instanceof LavenderBookScreen) {
			return 21000.0F;
		}
		return original.call();
	}
}
