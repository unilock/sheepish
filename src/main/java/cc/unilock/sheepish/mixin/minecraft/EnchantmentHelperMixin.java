package cc.unilock.sheepish.mixin.minecraft;

import cc.unilock.sheepish.SheepishConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@ModifyReturnValue(method = "selectEnchantment", at = @At(value = "RETURN"))
	private static List<EnchantmentInstance> filterEnchants(List<EnchantmentInstance> original) {
		original.removeIf(instance -> SheepishConfig.CONFIG.ignoredEnchantments.get().contains(instance.enchantment.getRegisteredName()));
		return original;
	}
}
