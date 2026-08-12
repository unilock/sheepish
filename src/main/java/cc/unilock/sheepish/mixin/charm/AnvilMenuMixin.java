package cc.unilock.sheepish.mixin.charm;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.charmony.event.AnvilUpdateEvent;

import java.util.Optional;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
	@Shadow
	@Final
	private DataSlot cost;

	@Shadow
	public int repairItemCountCost;

	public AnvilMenuMixin(@Nullable MenuType<?> type, int containerId, Inventory playerInventory, ContainerLevelAccess access) {
		super(type, containerId, playerInventory, access);
		throw new AssertionError();
	}

	@Inject(
			method = "createResult",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
					ordinal = 1,
					shift = At.Shift.BEFORE
			),
			cancellable = true
	)
	private void hookCreateResult(CallbackInfo ci, @Local(ordinal = 0) ItemStack input, @Local long baseCost) {
		Optional<AnvilUpdateEvent.AnvilRecipe> result = AnvilUpdateEvent.INSTANCE.invoke(this.player, input, this.inputSlots.getItem(1), baseCost);
		if (result.isPresent()) {
			AnvilUpdateEvent.AnvilRecipe recipe = result.get();
			this.resultSlots.setItem(0, recipe.output);
			this.cost.set(recipe.experienceCost);
			this.repairItemCountCost = recipe.materialCost;
			ci.cancel();
		}
	}
}
