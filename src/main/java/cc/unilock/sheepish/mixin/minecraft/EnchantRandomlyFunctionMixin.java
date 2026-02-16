package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.stream.Stream;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {
	@WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;toList()Ljava/util/List;"))
	private static List<EnchantmentInstance> filterEnchants(Stream<EnchantmentInstance> stream, Operation<List<EnchantmentInstance>> original) {
		return original.call(stream.filter(instance -> !CONFIG.ignoredEnchantments.value().contains(instance.enchantment.getRegisteredName())));
	}
}
