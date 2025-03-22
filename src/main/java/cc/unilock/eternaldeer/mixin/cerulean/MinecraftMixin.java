package cc.unilock.eternaldeer.mixin.cerulean;

import com.llamalad7.mixinextras.sugar.Local;
import fmt.cerulean.block.entity.WellBlockEntity;
import fmt.cerulean.client.particle.StarParticleType;
import fmt.cerulean.flow.FlowOutreach;
import fmt.cerulean.flow.FlowState;
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
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow @Nullable public ClientLevel level;
	@Shadow @Nullable public MultiPlayerGameMode gameMode;

	@Unique
	private static final MethodHandle GET_EXPORTED_STATE;
	@Unique
	private static final MethodHandle CREATE_PARTICLE;

	static {
		try {
			GET_EXPORTED_STATE = MethodHandles.publicLookup().findVirtual(FlowOutreach.class, "getExportedState", MethodType.methodType(FlowState.class, Direction.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Cerulean's FlowOutreach#getExportedState", e);
		}
		try {
			CREATE_PARTICLE = MethodHandles.publicLookup().findStatic(WellBlockEntity.class, "createParticle", MethodType.methodType(StarParticleType.class, FlowState.class, boolean.class, RandomSource.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Cerulean's WellBlockEntity#createParticle", e);
		}
	}

	@Inject(method = "continueAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;addBlockHitEffects(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;)V", shift = At.Shift.AFTER, ordinal = 1))
	private void cerulean$starwellBreaking(boolean breaking, CallbackInfo ci, @Local BlockHitResult blockHitResult, @Local BlockPos pos, @Local Direction direction) {
		BlockEntity be = this.level.getBlockEntity(pos);
		if (be != null && WellBlockEntity.class.isAssignableFrom(be.getClass())) {
			RandomSource random = this.level.random;
			double x = pos.getX() + 0.5;
			double y = pos.getY() + 0.5;
			double z = pos.getZ() + 0.5;
			FlowState state;
			try {
				state = (FlowState) GET_EXPORTED_STATE.invoke(Direction.UP);
			} catch (Throwable e) {
				e.printStackTrace();
				return;
			}
			if (state == null) {
				return;
			}

			for (int i = 0; i < this.gameMode.getDestroyStage(); i++) {
				ParticleOptions star;
				try {
					star = (ParticleOptions) CREATE_PARTICLE.invoke(state, true, random);
				} catch (Throwable e) {
					e.printStackTrace();
					continue;
				}
				double vx = random.nextGaussian() * 0.15;
				double vy = random.nextGaussian() * 0.15;
				double vz = random.nextGaussian() * 0.15;

				this.level.addParticle(star, true, x, y, z, vx, vy, vz);
			}
		}
	}
}
