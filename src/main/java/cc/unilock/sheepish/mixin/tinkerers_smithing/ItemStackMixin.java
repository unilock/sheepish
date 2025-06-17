package cc.unilock.sheepish.mixin.tinkerers_smithing;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import folk.sisby.tinkerers_smithing.TinkerersSmithing;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Unique
	private static final MethodHandle IS_BROKEN;
	@Unique
	private static final MethodHandle IS_KEEPER;
	static {
		try {
			IS_BROKEN = MethodHandles.publicLookup().findStatic(TinkerersSmithing.class, "isBroken", MethodType.methodType(boolean.class, ItemStack.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Tinkerer's Smithing's TinkerersSmithing#isBroken", e);
		}
		try {
			IS_KEEPER = MethodHandles.publicLookup().findStatic(TinkerersSmithing.class, "isKeeper", MethodType.methodType(boolean.class, ItemStack.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Tinkerer's Smithing's TinkerersSmithing#isKeeper", e);
		}
	}

	@Unique
	private boolean isBroken() {
		try {
			return (boolean) IS_BROKEN.invoke((ItemStack) (Object) this);
		} catch (Throwable e) {
			throw new RuntimeException("Failed to invoke handle for Tinkerer's Smithing's TinkerersSmithing#isBroken");
		}
	}

	@Unique
	private boolean isKeeper() {
		try {
			return (boolean) IS_KEEPER.invoke((ItemStack) (Object) this);
		} catch (Throwable e) {
			throw new RuntimeException("Failed to invoke handle for Tinkerer's Smithing's TinkerersSmithing#isKeeper");
		}
	}

	@Inject(method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V", at = @At("HEAD"), cancellable = true)
	private void brokenNoDamage(int amount, LivingEntity entity, EquipmentSlot slot, CallbackInfo ci) {
		if (isKeeper() && isBroken()) ci.cancel();
	}

	@WrapOperation(method = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;damageItem(Lnet/minecraft/world/item/ItemStack;ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)I", remap = false), require = 0)
	private int dontBreakDamageItemKeepers(Item instance, ItemStack stack, int amount, LivingEntity entity, Consumer<Item> onBreak, Operation<Integer> original) {
		if (isKeeper()) {
			return 0;
		} else {
			return original.call(instance, stack, amount, entity, onBreak);
		}
	}

	@WrapWithCondition(method = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
	private boolean dontBreakShrinkKeepers(ItemStack instance, int amount) {
		return !isKeeper();
	}
}
