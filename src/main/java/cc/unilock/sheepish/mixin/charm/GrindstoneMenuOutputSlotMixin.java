package cc.unilock.sheepish.mixin.charm;

import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.charmony.event.GrindstoneEvents;

@Mixin(targets = "net/minecraft/world/inventory/GrindstoneMenu$4")
public class GrindstoneMenuOutputSlotMixin extends Slot {
	public GrindstoneMenuOutputSlotMixin(Container container, int slot, int x, int y) {
		super(container, slot, x, y);
		throw new AssertionError();
	}

	@Override
	public boolean mayPickup(Player player) {
		GrindstoneEvents.GrindstoneMenuInstance instance = GrindstoneEvents.instance(player);

		if (instance == null) {
			return super.mayPickup(player);
		}

		InteractionResult result = GrindstoneEvents.CAN_TAKE.invoke(instance, player);

		if (result == InteractionResult.SUCCESS) {
			return true;
		} else if (result == InteractionResult.FAIL) {
			return false;
		}

		return super.mayPickup(player);
	}

	@Inject(
			method = "onTake",
			at = @At("HEAD"),
			cancellable = true
	)
	private void hookOnTake(Player player, ItemStack stack, CallbackInfo ci) {
		GrindstoneEvents.GrindstoneMenuInstance instance = GrindstoneEvents.instance(player);
		if (instance != null && GrindstoneEvents.ON_TAKE.invoke(instance, player, stack)) {
			ci.cancel();
		}
	}
}
