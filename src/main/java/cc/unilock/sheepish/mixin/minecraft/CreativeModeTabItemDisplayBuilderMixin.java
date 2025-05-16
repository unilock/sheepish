package cc.unilock.sheepish.mixin.minecraft;

import cc.unilock.sheepish.Sheepish;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net/minecraft/world/item/CreativeModeTab$ItemDisplayBuilder")
public class CreativeModeTabItemDisplayBuilderMixin {
	@Shadow @Final private CreativeModeTab tab;

	@Inject(method = "accept", at = @At(value = "NEW", target = "(Ljava/lang/String;)Ljava/lang/IllegalStateException;"), cancellable = true)
	private void cancel(ItemStack stack, CreativeModeTab.TabVisibility tabVisibility, CallbackInfo ci) {
		Sheepish.LOGGER.error("Accidentally adding the same item stack twice " + stack.getDisplayName().getString() + " to a Creative Mode Tab: " + this.tab.getDisplayName().getString());
		ci.cancel();
	}
}
