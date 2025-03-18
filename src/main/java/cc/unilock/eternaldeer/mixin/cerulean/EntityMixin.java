package cc.unilock.eternaldeer.mixin.cerulean;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import fmt.cerulean.entity.PlasticSwimming;
import fmt.cerulean.registry.CeruleanFluids;
import fmt.cerulean.world.CeruleanDimensions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements PlasticSwimming {
	@Shadow public abstract Level level();

	@Unique
	private static final ResourceLocation SKIES;
	@Unique
	private static final Fluid POLYETHYLENE;
	@Unique
	private static final Fluid REALIZED_POLYETHYLENE;
	@Unique
	private static final Fluid REALIZED_POLYETHYLENE_FLOWING;

	static {
		try {
			SKIES = (ResourceLocation) CeruleanDimensions.class.getDeclaredField("SKIES").get(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		try {
			POLYETHYLENE = (Fluid) CeruleanFluids.class.getDeclaredField("POLYETHYLENE").get(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		try {
			REALIZED_POLYETHYLENE = (Fluid) CeruleanFluids.class.getDeclaredField("REALIZED_POLYETHYLENE").get(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		try {
			REALIZED_POLYETHYLENE_FLOWING = (Fluid) CeruleanFluids.class.getDeclaredField("REALIZED_POLYETHYLENE_FLOWING").get(null);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean cerulean$inPlastic = false;
	private boolean cerulean$inFakePlastic = false;

	@Inject(method = "doWaterSplashEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;floor(D)I"), cancellable = true)
	public void cerulean(CallbackInfo ci) {
		if (!this.level().dimensionTypeRegistration().unwrapKey().orElseThrow().location().equals(SKIES)) {
			return;
		}

		if (cerulean$inPlastic || cerulean$inFakePlastic) {
			ci.cancel();
		}
	}

	@Inject(method = "updateFluidHeightAndDoFluidPushing(Lnet/minecraft/tags/TagKey;D)Z", at = @At("HEAD"))
	public void cerulean$resetPlasticState(TagKey<Fluid> tag, double speed, CallbackInfoReturnable<Boolean> cir, @Share("water") LocalBooleanRef cerulean$inWater) {
		if (FluidTags.WATER.equals(tag)) {
			cerulean$inPlastic = false;
			cerulean$inFakePlastic = false;
			cerulean$inWater.set(true);
		}
	}

	@Inject(method = "updateFluidHeightAndDoFluidPushing()V", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"))
	public void cerulean$setPlasticState(CallbackInfo ci, @Local FluidState fluidState, @Share("water") LocalBooleanRef cerulean$water) {
		if (cerulean$water.get()) {
			Fluid fluid = fluidState.getType();
			if (fluid == POLYETHYLENE) {
				cerulean$inPlastic = true;
			}

			if (fluid == REALIZED_POLYETHYLENE || fluid == REALIZED_POLYETHYLENE_FLOWING) {
				cerulean$inFakePlastic = true;
			}

			cerulean$water.set(false);
		}
	}

	@Inject(method = "onGround", at = @At("HEAD"), cancellable = true)
	public void cerulean$itemMovement(CallbackInfoReturnable<Boolean> cir) {
		if (ItemEntity.class.isAssignableFrom(this.getClass()) && cerulean$inPlastic) {
			cir.setReturnValue(false);
		}
	}

	@Override
	public boolean cerulean$isInPlastic() {
		return cerulean$inPlastic;
	}
}
