package cc.unilock.eternaldeer.mixin.owo;

import cc.unilock.eternaldeer.mixin.minecraft.CreativeModeTabAccessor;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = OwoItemGroup.class, remap = false)
public class OwoItemGroupMixin {
	@ModifyReturnValue(method = "getBackgroundTexture", at = @At("RETURN"))
	private ResourceLocation checkNullBackgroundTexture(ResourceLocation original) {
		return original == null ? CreativeModeTabAccessor.getDEFAULT_BACKGROUND() : original;
	}
}
