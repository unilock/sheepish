package cc.unilock.eternaldeer.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeInventoryScreenMixin {
	@ModifyExpressionValue(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CreativeModeTab;getBackgroundTexture()Lnet/minecraft/resources/ResourceLocation;"))
	private ResourceLocation checkNullBackgroundTexture(ResourceLocation original) {
		return original == null ? CreativeModeTabAccessor.getDEFAULT_BACKGROUND() : original;
	}
}
