package cc.unilock.sheepish.mixin.minestuck;

import com.mraof.minestuck.client.gui.GoButton;
import com.mraof.minestuck.inventory.MachineContainerMenu;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GoButton.class)
public class GoButtonMixin {
	@Shadow @Final private MachineContainerMenu menu;

	@Inject(method = "getMessage", at = @At("HEAD"), cancellable = true)
	private void getMessage(CallbackInfoReturnable<Component> cir) {
		if (this.menu == null) cir.setReturnValue(Component.empty());
	}
}
