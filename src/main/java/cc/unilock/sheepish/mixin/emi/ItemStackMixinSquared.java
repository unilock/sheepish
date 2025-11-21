package cc.unilock.sheepish.mixin.emi;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ItemStack.class, priority = 1500)
public abstract class ItemStackMixinSquared {
	@Shadow
	public abstract Item getItem();

	@TargetHandler(
			mixin = "dev.emi.emi.mixin.ItemStackMixin",
			name = "getTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;Lorg/spongepowered/asm/mixin/injection/callback/CallbackInfoReturnable;)V"
	)
	@ModifyArg(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Ldev/emi/emi/EmiUtil;getModName(Ljava/lang/String;)Ljava/lang/String;"))
	private String getModName(String namespace) {
		return this.getItem().getCreatorModId(ItemStack.class.cast(this));
	}
}
