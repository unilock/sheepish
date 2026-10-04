package cc.unilock.sheepish.mixin.partytrick;

import com.ashaxolotl.partytrick.misc.PartyTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.enjarai.trickster.spell.ItemTriggerHelper;
import dev.enjarai.trickster.spell.fragment.VectorFragment;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.neoforged.neoforge.common.CommonHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(CommonHooks.class)
public class CommonHooksMixin {
	@WrapOperation(method = "onPlaceItemIntoWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;"))
	private static InteractionResult castOnUseOnBlock(Item instance, UseOnContext context, Operation<InteractionResult> original) {
		var itemStack = context.getItemInHand().copy();
		var returnValue = original.call(instance, context);

		if (context.getPlayer() instanceof ServerPlayer serverPlayer
				&& returnValue.consumesAction()
				&& itemStack.is(PartyTags.CAST_ON_USE_WHITELIST)
		) {
			ItemTriggerHelper.trigger(serverPlayer, itemStack, List.of(VectorFragment.of(context.getClickedPos())));
		}
		return returnValue;
	}
}
