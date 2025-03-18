package cc.unilock.eternaldeer.mixin.extended_industrialization;

import net.minecraft.world.item.crafting.Ingredient;
import net.swedz.extended_industrialization.EIArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EIArmorMaterials.class, remap = false)
public class EIArmorMaterialsMixin {
	@Inject(method = "lambda$createNanoMaterial$1", at = @At("HEAD"), cancellable = true)
	private static void nanoArmor(CallbackInfoReturnable<Ingredient> cir) {
		cir.setReturnValue(Ingredient.EMPTY);
	}
}
