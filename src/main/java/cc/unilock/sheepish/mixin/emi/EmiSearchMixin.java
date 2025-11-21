package cc.unilock.sheepish.mixin.emi;

import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.search.EmiSearch;
import dev.emi.emi.search.SearchStack;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = EmiSearch.class, remap = false)
public class EmiSearchMixin {
	@ModifyArg(method = "bake", at = @At(value = "INVOKE", target = "Ldev/emi/emi/EmiUtil;getModName(Ljava/lang/String;)Ljava/lang/String;"))
	private static String getModName(String namespace, @Local SearchStack searchStack) {
		ItemStack stack = searchStack.stack.getItemStack();
		return stack.getItem().getCreatorModId(stack);
	} 
}
