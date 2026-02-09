package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {
	@ModifyExpressionValue(method = "run", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;toList()Ljava/util/List;"))
	private static List<EnchantmentInstance> filterEnchants(List<EnchantmentInstance> original) {
		original.removeIf(instance -> CONFIG.ignoredEnchantments.value().contains(instance.enchantment.getRegisteredName()));
		return original;
	}
}
