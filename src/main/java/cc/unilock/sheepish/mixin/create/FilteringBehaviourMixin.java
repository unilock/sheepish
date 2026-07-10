package cc.unilock.sheepish.mixin.create;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = FilteringBehaviour.class, remap = false)
public class FilteringBehaviourMixin {
	@WrapOperation(method = "getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I", remap = true))
	private int getMaxStackSize$getMaxStackSize(ItemStack instance, Operation<Integer> original) {
		return Math.max(original.call(instance), CONFIG.createFilterMaxStackSize.value());
	}
}
