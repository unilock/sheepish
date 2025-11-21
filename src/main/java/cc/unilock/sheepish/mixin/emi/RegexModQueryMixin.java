package cc.unilock.sheepish.mixin.emi;

import com.llamalad7.mixinextras.sugar.Local;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.search.RegexModQuery;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = RegexModQuery.class, remap = false)
public class RegexModQueryMixin {
	@ModifyArg(method = "matches", at = @At(value = "INVOKE", target = "Ldev/emi/emi/EmiUtil;getModName(Ljava/lang/String;)Ljava/lang/String;"))
	private static String getModName(String namespace, @Local(argsOnly = true) EmiStack emiStack) {
		ItemStack stack = emiStack.getItemStack();
		return stack.getItem().getCreatorModId(stack);
	}
}
