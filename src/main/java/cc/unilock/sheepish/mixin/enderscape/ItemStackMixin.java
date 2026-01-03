package cc.unilock.sheepish.mixin.enderscape;

import net.bunten.enderscape.item.component.FueledTool;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.bunten.enderscape.item.ItemStackContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true)
	public void enderscape$hurtAndBreak(int i, ServerLevel level, LivingEntity entity, Consumer<Item> consumer, CallbackInfo ci) {
		if (FueledTool.is(ItemStack.class.cast(this))) {
			FueledTool.useFuel(new ItemStackContext(ItemStack.class.cast(this), level, entity));
			ci.cancel();
		}
	}
}
