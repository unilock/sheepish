package cc.unilock.sheepish.mixin.mythicmetals;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin {
	@Unique
	private static final MethodHandle IS_WEARING;
	@Unique
	private static final Item CELESTIUM_ELYTRA;
	@Unique
	private static final ResourceLocation CELESTIUM_ELYTRA_TEXTURE = ResourceLocation.fromNamespaceAndPath("mythicmetals", "textures/models/celestium_elytra.png");

	static {
		try {
			IS_WEARING = MethodHandles.publicLookup().findStatic(Class.forName("nourl.mythicmetals.armor.CelestiumElytra"), "isWearing", MethodType.methodType(boolean.class, LivingEntity.class));
		} catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Mythic Metals' CelestiumElytra#isWearing", e);
		}
		try {
			CELESTIUM_ELYTRA = (Item) MethodHandles.publicLookup().findStaticGetter(Class.forName("nourl.mythicmetals.armor.MythicArmor"), "CELESTIUM_ELYTRA", Item.class).invoke();
		} catch (Throwable e) {
			throw new RuntimeException("Failed to find handle for Mythic Metals' MythicArmor.CELESTIUM_ELYTRA", e);
		}
	}

	@ModifyReturnValue(method = "shouldRender", at = @At("RETURN"))
	private boolean shouldRender(boolean original, ItemStack stack, LivingEntity entity) {
		try {
			return original || (boolean) IS_WEARING.invoke(entity);
		} catch (Throwable e) {
			throw new RuntimeException("Failed to invoke handle for Mythic Metals' CelestiumElytra#isWearing", e);
		}
	}

	@ModifyReturnValue(method = "getElytraTexture", at = @At("RETURN"))
	private ResourceLocation getElytraTexture(ResourceLocation original, ItemStack stack) {
		return stack.is(CELESTIUM_ELYTRA) ? CELESTIUM_ELYTRA_TEXTURE : original;
	}
}
