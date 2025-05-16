package cc.unilock.sheepish.mixin.wiretap;

import com.llamalad7.mixinextras.sugar.Local;
import de.maxhenkel.wiretap.Wiretap;
import de.maxhenkel.wiretap.utils.HeadUtils;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import java.util.UUID;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
	@Shadow @Nullable private String itemName;

	@Unique
	private UUID currentResultId;
	@Unique
	private ItemStack currentInputItem;

	@Unique
	private static final MethodHandle CREATE_MICROPHONE;
	@Unique
	private static final MethodHandle CREATE_SPEAKER;
	static {
		try {
			CREATE_MICROPHONE = MethodHandles.publicLookup().findStatic(HeadUtils.class, "createMicrophone", MethodType.methodType(ItemStack.class, UUID.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Wiretap's HeadUtils#createMicrphone", e);
		}
		try {
			CREATE_SPEAKER = MethodHandles.publicLookup().findStatic(HeadUtils.class, "createSpeaker", MethodType.methodType(ItemStack.class, UUID.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Wiretap's HeadUtils#createSpeaker", e);
		}
	}

	public AnvilMenuMixin(@Nullable MenuType<?> type, int containerId, Inventory playerInventory, ContainerLevelAccess access) {
		super(type, containerId, playerInventory, access);
		throw new IllegalStateException();
	}

	@Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V", ordinal = 4, shift = At.Shift.AFTER))
	private void createResult(CallbackInfo ci, @Local ItemStack itemStack) {
		if (!Wiretap.SERVER_CONFIG.anvilCrafting.get()) {
			return;
		}
		if (player.level().isClientSide()) {
			return;
		}
		if (!itemStack.getItem().equals(Items.CALIBRATED_SCULK_SENSOR)) {
			return;
		}

		if (itemName == null || !itemName.equalsIgnoreCase("wiretap")) {
			return;
		}

		currentResultId = UUID.randomUUID();
		ItemStack microphone; try { microphone = (ItemStack) CREATE_MICROPHONE.invoke(currentResultId); } catch (Throwable e) { throw new RuntimeException(e); }
		resultSlots.setItem(0, microphone);
		currentInputItem = itemStack.copy();
	}

	@Inject(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Container;setItem(ILnet/minecraft/world/item/ItemStack;)V", ordinal = 0, shift = At.Shift.AFTER))
	private void onTake(Player player, ItemStack result, CallbackInfo ci) {
		if (!Wiretap.SERVER_CONFIG.anvilCrafting.get()) {
			return;
		}
		if (player.level().isClientSide()) {
			return;
		}
		if (currentResultId == null || currentInputItem == null) {
			return;
		}
		ItemStack inputItem = currentInputItem.copy();
		inputItem.setCount(currentInputItem.getCount() - 1);
		if (inputItem.getCount() <= 0) {
			inputItem = ItemStack.EMPTY;
		}
		ItemStack speaker; try { speaker = (ItemStack) CREATE_SPEAKER.invoke(currentResultId); } catch (Throwable e) { throw new RuntimeException(e); }
		currentResultId = null;
		currentInputItem = null;
		// setItem calls createResult again
		inputSlots.setItem(0, inputItem);

		boolean added = player.getInventory().add(speaker);
		if (!added || !speaker.isEmpty()) {
			player.drop(speaker, false);
		}
	}
}
