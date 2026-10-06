package cc.unilock.sheepish.mixin.affinity;

import io.wispforest.affinity.misc.ArcaneFadeFluid;
import io.wispforest.affinity.object.AffinityBlocks;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import org.sinytra.connector.mod.compat.FluidHandlerCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityExtension {
	@Unique
	private boolean affinity$touchingBleach = false;

	@Inject(method = "updateInWaterStateAndDoFluidPushing", at = @At("RETURN"))
	protected void updateFadeState(CallbackInfoReturnable<Boolean> cir) {
		boolean wasTouchingFade = this.affinity$touchingBleach;
		this.affinity$touchingBleach = this.isInFluidType(FluidHandlerCompat.getFabricFluidType(AffinityBlocks.Fluids.ARCANE_FADE)) || this.isInFluidType(FluidHandlerCompat.getFabricFluidType(AffinityBlocks.Fluids.ARCANE_FADE_FLOWING));

		if (this.affinity$touchingBleach && !wasTouchingFade) {
			ArcaneFadeFluid.ENTITY_TOUCH_EVENT.invoker().onTouch((Entity) (Object) this);
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	protected void invokeFadeTickEvent(CallbackInfo ci) {
		if (!this.affinity$touchingBleach) return;
		ArcaneFadeFluid.ENTITY_TICK_IN_FADE_EVENT.invoker().onTouch((Entity) (Object) this);
	}
}
