package cc.unilock.sheepish.mixin.enigmaticlegacyplus;

import auviotre.enigmatic.legacy.contents.loot.modifiers.SpecialLootModifier;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = SpecialLootModifier.class, remap = false)
public class SpecialLootModifierMixin {
	@WrapOperation(method = "doApply", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;contains(Ljava/lang/String;)Z", ordinal = 0))
	private boolean doApply$contains(CompoundTag instance, String key, Operation<Boolean> original) {
		return original.call(instance, key) || CONFIG.disableEnigmaticEye.value();
	}
}
