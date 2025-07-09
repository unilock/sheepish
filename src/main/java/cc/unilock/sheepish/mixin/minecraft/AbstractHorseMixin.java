package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(AbstractHorse.class)
public abstract class AbstractHorseMixin extends LivingEntity {
	protected AbstractHorseMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
		throw new AssertionError();
	}

	@WrapOperation(method = "setOffspringAttributes", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;MIN_HEALTH:F", opcode = Opcodes.GETSTATIC))
	private float setOffspringAttributes$getMinHealth(Operation<Float> original, AgeableMob parent) {
		if (CONFIG.horseStonks.value()) {
			return (float) Math.min(this.getAttributeBaseValue(Attributes.MAX_HEALTH), parent.getAttributeBaseValue(Attributes.MAX_HEALTH));
		} else {
			return original.call();
		}
	}

	@WrapOperation(method = "setOffspringAttributes", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;MIN_JUMP_STRENGTH:F", opcode = Opcodes.GETSTATIC))
	private float setOffspringAttributes$getMinJumpStrength(Operation<Float> original, AgeableMob parent) {
		if (CONFIG.horseStonks.value()) {
			return (float) Math.min(this.getAttributeBaseValue(Attributes.JUMP_STRENGTH), parent.getAttributeBaseValue(Attributes.JUMP_STRENGTH));
		} else {
			return original.call();
		}
	}

	@WrapOperation(method = "setOffspringAttributes", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;MIN_MOVEMENT_SPEED:F", opcode = Opcodes.GETSTATIC))
	private float setOffspringAttributes$getMinMovementSpeed(Operation<Float> original, AgeableMob parent) {
		if (CONFIG.horseStonks.value()) {
			return (float) Math.min(this.getAttributeBaseValue(Attributes.MOVEMENT_SPEED), parent.getAttributeBaseValue(Attributes.MOVEMENT_SPEED));
		} else {
			return original.call();
		}
	}
}
