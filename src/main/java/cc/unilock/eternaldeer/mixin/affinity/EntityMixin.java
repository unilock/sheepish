package cc.unilock.eternaldeer.mixin.affinity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.wispforest.affinity.misc.ArcaneFadeFluid;
import io.wispforest.affinity.object.AffinityBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import org.sinytra.connector.mod.compat.FluidHandlerCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityExtension {
	@Shadow protected boolean wasTouchingWater;
	@Shadow @Deprecated public abstract boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> fluidTag, double motionScale);

	@Unique
	private static final TagKey<Fluid> ARCANE_FADE = TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath("affinity", "arcane_fade"));
	@Unique
	private boolean affinity$touchingBleach = false;

	@ModifyReturnValue(method = "updateInWaterStateAndDoFluidPushing", at = @At("RETURN"))
	protected boolean updateFadeState(boolean value) {
		boolean wasTouchingFade = this.affinity$touchingBleach;
		this.wasTouchingWater |= this.affinity$touchingBleach = this.updateFluidHeightAndDoFluidPushing(ARCANE_FADE, 0.014);

		if (this.affinity$touchingBleach && !wasTouchingFade) {
			ArcaneFadeFluid.ENTITY_TOUCH_EVENT.invoker().onTouch((Entity) (Object) this);
		}

		return value || this.wasTouchingWater;
	}

	@Inject(method = "tick", at = @At("TAIL"))
	protected void invokeFadeTickEvent(CallbackInfo ci) {
		if (!this.affinity$touchingBleach) return;
		ArcaneFadeFluid.ENTITY_TICK_IN_FADE_EVENT.invoker().onTouch((Entity) (Object) this);
	}

	@Inject(method = "updateFluidHeightAndDoFluidPushing(Lnet/minecraft/tags/TagKey;D)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateFluidHeightAndDoFluidPushing()V", shift = At.Shift.AFTER), cancellable = true)
	private void updateFluidHeightAndDoFluidPushing$postUpdateFluidHeightAndDoFluidPushing(TagKey<Fluid> fluidTag, double motionScale, CallbackInfoReturnable<Boolean> cir) {
		if (ARCANE_FADE.equals(fluidTag)) cir.setReturnValue(this.isInFluidType(FluidHandlerCompat.getFabricFluidType(AffinityBlocks.ARCANE_FADE.fluid)));
	}
}
