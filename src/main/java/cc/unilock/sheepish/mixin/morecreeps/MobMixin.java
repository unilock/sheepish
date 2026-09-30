package cc.unilock.sheepish.mixin.morecreeps;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(Mob.class)
public abstract class MobMixin extends Entity {
	public MobMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
		throw new AssertionError();
	}

	@WrapMethod(method = "playAmbientSound")
	private void playAmbientSound(Operation<Void> original) {
		if (CONFIG.disableMoreCreepsAmbientSounds.value() || BuiltInRegistries.ENTITY_TYPE.getKey(this.getType()).getNamespace().equals("morecreeps")) {
			return;
		}

		original.call();
	}
}
