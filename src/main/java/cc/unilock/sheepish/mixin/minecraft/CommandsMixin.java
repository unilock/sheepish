package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Commands.class)
public class CommandsMixin {
	@ModifyExpressionValue(method = "performCommand", at = @At(value = "FIELD", target = "Lnet/minecraft/SharedConstants;IS_RUNNING_IN_IDE:Z", opcode = Opcodes.GETSTATIC))
	private boolean performCommand$getIsRunningInIde(boolean original) {
		return true;
	}

	@Redirect(method = "performCommand", at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V", ordinal = 2))
	private void performCommand$sendFailure(CommandSourceStack instance, Component message) {
		// NO-OP
	}
}
