package cc.unilock.sheepish.mixin.joy;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow public abstract ItemStack getItemBySlot(EquipmentSlot slot);

	@Unique
	private static final TagKey<Item> GLIDERS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "gliders"));

	@ModifyArg(method = "updateFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"))
	private boolean updateFallFlying$getFlag(boolean original) {
		return original || this.getItemBySlot(EquipmentSlot.CHEST).is(GLIDERS);
	} 
}
