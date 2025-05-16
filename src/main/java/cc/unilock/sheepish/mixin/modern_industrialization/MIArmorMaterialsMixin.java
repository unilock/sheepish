package cc.unilock.sheepish.mixin.modern_industrialization;

import aztech.modern_industrialization.items.armor.MIArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MIArmorMaterials.class, remap = false)
public class MIArmorMaterialsMixin {
	@Inject(method = "lambda$static$0", at = @At("HEAD"), cancellable = true)
	private static void dieselJetpack(CallbackInfoReturnable<Ingredient> cir) {
		cir.setReturnValue(Ingredient.EMPTY);
	}

	@Inject(method = "lambda$static$2", at = @At("HEAD"), cancellable = true)
	private static void graviChestPlate(CallbackInfoReturnable<Ingredient> cir) {
		cir.setReturnValue(Ingredient.EMPTY);
	}

	@Inject(method = "lambda$static$7", at = @At("HEAD"), cancellable = true)
	private static void quantumArmor(CallbackInfoReturnable<Ingredient> cir) {
		cir.setReturnValue(Ingredient.EMPTY);
	}
}
