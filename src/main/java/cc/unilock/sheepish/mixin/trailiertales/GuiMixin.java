package cc.unilock.sheepish.mixin.trailiertales;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalDoubleRef;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Mth;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@Mixin(Gui.class)
public class GuiMixin {
	@Unique
	private static final MethodHandle GET_HAUNT_PROGRESS;
	static {
		try {
			GET_HAUNT_PROGRESS = MethodHandles.privateLookupIn(Gui.class, MethodHandles.lookup()).findStatic(Gui.class, "trailierTales$getHauntProgress", MethodType.methodType(float.class));
		} catch (IllegalAccessException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to find handle for Trailier Tales's Gui#trailierTales$getHauntProgress mixin method", e);
		}
	}

	@ModifyExpressionValue(
			method = "renderHealthLevel",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/player/Player;getAttributeValue(Lnet/minecraft/core/Holder;)D"
			),
			slice = @Slice(
					from = @At(
							value = "FIELD",
							target = "Lnet/minecraft/world/entity/ai/attributes/Attributes;MAX_HEALTH:Lnet/minecraft/core/Holder;",
							opcode = Opcodes.GETSTATIC
					)
			)
	)
	private double trailierTales$captureMaxHealthAttribute(
			double original,
			@Share("trailierTales$maxHealthAttribute") LocalDoubleRef maxHealthAttribute
	) {
		maxHealthAttribute.set(original);
		return original;
	}

	@ModifyExpressionValue(
			method = "renderHealthLevel",
			at = @At(
					value = "INVOKE",
					target = "Ljava/lang/Math;max(II)I",
					ordinal = 0
			),
			slice = @Slice(
					from = @At(
							value = "FIELD",
							target = "Lnet/minecraft/world/entity/ai/attributes/Attributes;MAX_HEALTH:Lnet/minecraft/core/Holder;",
							opcode = Opcodes.GETSTATIC
					)
			)
	)
	private int trailierTales$lerpBackHealth(
			int original,
			@Share("trailierTales$maxHealthAttribute") LocalDoubleRef maxHealthAttribute
	) {
		try {
			return (int) Mth.lerp((float) GET_HAUNT_PROGRESS.invoke(), original, maxHealthAttribute.get());
		} catch (Throwable e) {
			throw new RuntimeException("Failed to invoke handle for Trailier Tales's Gui#trailierTales$getHauntProgress mixin method", e);
		}
	}
}
