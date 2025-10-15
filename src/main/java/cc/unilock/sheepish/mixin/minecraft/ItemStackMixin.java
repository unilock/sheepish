package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow
	@Nullable
	public abstract <T> T remove(DataComponentType<? extends T> component);

	@WrapMethod(method = "set")
	private <T> T set(DataComponentType<? super T> component, T value, Operation<T> original) {
		if (CONFIG.disableRepairCost.value() && DataComponents.REPAIR_COST.equals(component)) {
			this.remove(DataComponents.REPAIR_COST);
			return (T) Integer.valueOf(0);
		} else {
			return original.call(component, value);
		}
	}
}
