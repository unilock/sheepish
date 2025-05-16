package cc.unilock.sheepish.mixin.anthropophagy;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import moriyashiine.anthropophagy.common.item.FleshItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
	@WrapOperation(method = "burn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/AbstractCookingRecipe;assemble(Lnet/minecraft/world/item/crafting/SingleRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;"))
	private static ItemStack burn(AbstractCookingRecipe instance, SingleRecipeInput input, HolderLookup.Provider registries, Operation<ItemStack> original, @Local(argsOnly = true) NonNullList<ItemStack> inventory) {
		ItemStack stack = original.call(instance, input, registries);
		if (stack.getItem() instanceof FleshItem) {
			ItemStack toCook = inventory.getFirst();
			if (toCook.getItem() instanceof FleshItem) {
				stack = stack.copy();
				FleshItem.setOwner(stack, FleshItem.getOwnerName(toCook), FleshItem.isOwnerPlayer(toCook));
			}
		}
		return stack;
	}
}
