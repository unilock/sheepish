package cc.unilock.eternaldeer.mixin.mythicmetals;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin {
	@Unique
	private static final Method isWearing;
	@Unique
	private static final Item CELESTIUM_ELYTRA;
	@Unique
	private static final ResourceLocation CELESTIUM_ELYTRA_TEXTURE = ResourceLocation.fromNamespaceAndPath("mythicmetals", "textures/models/celestium_elytra.png");

	static {
		try {
			isWearing = Class.forName("nourl.mythicmetals.armor.CelestiumElytra").getDeclaredMethod("isWearing", LivingEntity.class);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to reflect CelestiumElytra#isWearing", e);
		}
		try {
			CELESTIUM_ELYTRA = (Item) Class.forName("nourl.mythicmetals.armor.MythicArmor").getDeclaredField("CELESTIUM_ELYTRA").get(null);
		} catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException("Failed to reflect MythicArmor.CELESTIUM_ELYTRA", e);
		}
	}

	@Unique
	private boolean isWearing(LivingEntity entity) {
		try {
			return (boolean) isWearing.invoke(null, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Failed to invoke CelestiumElytra#isWearing", e);
		}
	}

	@ModifyReturnValue(method = "shouldRender", at = @At("RETURN"))
	private boolean shouldRender(boolean original, ItemStack stack, LivingEntity entity) {
		return original || isWearing(entity);
	}

	@ModifyReturnValue(method = "getElytraTexture", at = @At("RETURN"))
	private ResourceLocation getElytraTexture(ResourceLocation original, ItemStack stack) {
		return stack.is(CELESTIUM_ELYTRA) ? CELESTIUM_ELYTRA_TEXTURE : original;
	}
}
