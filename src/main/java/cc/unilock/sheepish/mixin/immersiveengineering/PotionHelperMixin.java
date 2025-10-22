package cc.unilock.sheepish.mixin.immersiveengineering;

import blusunrize.immersiveengineering.api.crafting.IngredientWithSize;
import blusunrize.immersiveengineering.common.crafting.PotionHelper;
import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionHelper.class)
public class PotionHelperMixin {
	@WrapOperation(method = "applyToAllPotionRecipes", at = @At(value = "INVOKE", target = "Lblusunrize/immersiveengineering/common/crafting/PotionHelper$PotionRecipeProcessor;apply(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lblusunrize/immersiveengineering/api/crafting/IngredientWithSize;)V"))
	private static void applyToAllPotionRecipes$apply(PotionHelper.PotionRecipeProcessor instance, Holder<Potion> output, Holder<Potion> input, IngredientWithSize reagent, Operation<Void> original) {
		if (output.unwrapKey().isPresent() && input.unwrapKey().isPresent()) {
			original.call(instance, output, input, reagent);
		} else {
			Sheepish.LOGGER.error("Attempted to apply to potion recipe with unbound input and/or output! input: \"{}\", output: \"{}\", reagent: \"{}\"", BuiltInRegistries.POTION.getKey(input.value()), BuiltInRegistries.POTION.getKey(output.value()), reagent.getBaseIngredient().getItems()[0].getItem());
		}
	}
}
