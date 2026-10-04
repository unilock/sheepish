package cc.unilock.sheepish.mixin.partytrick;

import com.ashaxolotl.partytrick.misc.PartyTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.enjarai.trickster.spell.ItemTriggerHelper;
import dev.enjarai.trickster.spell.fragment.EntityFragment;
import dev.enjarai.trickster.spell.fragment.VoidFragment;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@WrapOperation(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;finishUsingItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;"))
	private ItemStack castOnConsumption(Item instance, ItemStack stack, Level level, LivingEntity livingEntity, Operation<ItemStack> original) {
		var itemStack = stack.copy();
		var returnValue = original.call(instance, stack, level, livingEntity);

		if (livingEntity instanceof ServerPlayer serverPlayer
				&& !(itemStack.is(PartyTags.CAST_ON_CONSUMPTION_BLACKLIST))
		) {
			ItemTriggerHelper.trigger(serverPlayer, itemStack, List.of());
		}
		return returnValue;
	}

	@WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;"))
	private InteractionResultHolder<ItemStack> castOnUse(Item instance, Level level, Player player, InteractionHand usedHand, Operation<InteractionResultHolder<ItemStack>> original) {
		var itemStack =  player.getItemInHand(usedHand).copy();
		var returnValue = original.call(instance, level, player, usedHand);

		if (player instanceof ServerPlayer serverPlayer
				&& returnValue.getResult().consumesAction()
				&& itemStack.is(PartyTags.CAST_ON_USE_WHITELIST)
		) {
			ItemTriggerHelper.trigger(serverPlayer, itemStack, List.of(VoidFragment.INSTANCE));
		}
		return returnValue;
	}

//	@WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;"))
//	private ActionResult castOnUseOnBlock(Item instance, ItemUsageContext context, Operation<ActionResult> original) {
//		var itemStack = context.getStack().copy();
//		var returnValue = original.call(instance, context);
//
//		if (context.getPlayer() instanceof ServerPlayerEntity player
//				&& returnValue.isAccepted()
//				&& itemStack.isIn(PartyTags.CAST_ON_USE_WHITELIST)
//		) {
//			ItemTriggerHelper.trigger(player, itemStack, List.of(VectorFragment.of(context.getBlockPos())));
//		}
//		return returnValue;
//	}

	@WrapOperation(method = "interactLivingEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;interactLivingEntity(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;"))
	private InteractionResult castOnUseOnEntity(Item instance, ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand, Operation<InteractionResult> original) {
		var itemStack = stack.copy();
		var returnValue = original.call(instance, stack, player, interactionTarget, usedHand);

		if (player instanceof ServerPlayer serverPlayer
				&& returnValue.consumesAction()
				&& itemStack.is(PartyTags.CAST_ON_USE_WHITELIST)
		) {
			ItemTriggerHelper.trigger(serverPlayer, itemStack, List.of(EntityFragment.from(interactionTarget)));
		}
		return returnValue;
	}
}
