package cc.unilock.eternaldeer.mixin.cerulean;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow @Nullable public ClientLevel level;
	@Shadow @Nullable public MultiPlayerGameMode gameMode;

	@Unique
	private static final Class<?> WELL_BLOCK_ENTITY_CLASS;
	@Unique
	private static final Method GET_EXPORTED_STATE_METHOD;
	@Unique
	private static final Class<?> FLOW_STATE_CLASS;
	@Unique
	private static final Method CREATE_PARTICLE_METHOD;

	static {
		try {
			WELL_BLOCK_ENTITY_CLASS = Class.forName("fmt.cerulean.block.entity.WellBlockEntity");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to reflect WellBlockEntity", e);
		}
		try {
			GET_EXPORTED_STATE_METHOD = Class.forName("fmt.cerulean.flow.FlowOutreach").getDeclaredMethod("getExportedState", Direction.class);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to reflect FlowOutreach#getExportedState", e);
		}
		try {
			FLOW_STATE_CLASS = Class.forName("fmt.cerulean.flow.FlowState");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to reflect FlowState", e);
		}
		try {
			CREATE_PARTICLE_METHOD = WELL_BLOCK_ENTITY_CLASS.getDeclaredMethod("createParticle", FLOW_STATE_CLASS, boolean.class, RandomSource.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Failed to reflect WellBlockEntity#createParticle", e);
		}
	}

	@Unique
	private Object getExportedState(Object instance, Direction direction) {
		try {
			return GET_EXPORTED_STATE_METHOD.invoke(instance, direction);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Failed to invoke FlowOutreach#getExportedState", e);
		}
	}

	@Unique
	private ParticleOptions createParticle(Object instance, Object state, boolean tubular, RandomSource random) {
		try {
			return (ParticleOptions) CREATE_PARTICLE_METHOD.invoke(instance, FLOW_STATE_CLASS.cast(state), tubular, random);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Failed to invoke WellBlockEntity#createParticle", e);
		}
	}

	@Inject(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;addBlockHitEffects(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;)V", shift = At.Shift.AFTER, ordinal = 1))
	private void cerulean$starwellBreaking(boolean breaking, CallbackInfo ci, @Local BlockHitResult blockHitResult, @Local BlockPos pos, @Local Direction direction) {
		BlockEntity be = this.level.getBlockEntity(pos);
		if (be != null && WELL_BLOCK_ENTITY_CLASS.isAssignableFrom(be.getClass())) {
			RandomSource random = this.level.random;
			double x = pos.getX() + 0.5;
			double y = pos.getY() + 0.5;
			double z = pos.getZ() + 0.5;
			Object state = getExportedState(be, Direction.UP);
			if (state == null) {
				return;
			}

			for (int i = 0; i < this.gameMode.getDestroyStage(); i++) {
				ParticleOptions star = createParticle(be, state, true, random);
				double vx = random.nextGaussian() * 0.15;
				double vy = random.nextGaussian() * 0.15;
				double vz = random.nextGaussian() * 0.15;

				this.level.addParticle(star, true, x, y, z, vx, vy, vz);
			}
		}
	}
}
