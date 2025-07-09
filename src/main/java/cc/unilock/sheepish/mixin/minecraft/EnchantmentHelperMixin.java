package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@ModifyReturnValue(method = "selectEnchantment", at = @At(value = "RETURN"))
	private static List<EnchantmentInstance> filterEnchants(List<EnchantmentInstance> original) {
		original.removeIf(instance -> CONFIG.ignoredEnchantments.value().contains(instance.enchantment.getRegisteredName()));
		return original;
	}
}
