package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
	@ModifyReturnValue(method = "isMergable", at = @At("RETURN"))
	private boolean isMergable(boolean original) {
		if (CONFIG.droppedItemsDoNotStack.value()) {
			return false;
		} else {
			return original;
		}
	}
}
