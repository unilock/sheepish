package cc.unilock.sheepish.mixin.frozenlib;

import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Ingredient.ItemValue.class)
public class ItemValueMixin {
	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;mapCodec(Ljava/util/function/Function;)Lcom/mojang/serialization/MapCodec;"))
	private static MapCodec<Ingredient.ItemValue> mapCodec(Function<RecordCodecBuilder.Instance<Ingredient.ItemValue>, ? extends App<RecordCodecBuilder.Mu<Ingredient.ItemValue>, Ingredient.ItemValue>> builder) {
		return RecordCodecBuilder.mapCodec(instance ->
				instance.group(
						ItemStack.SIMPLE_ITEM_CODEC.fieldOf("item").forGetter(Ingredient.ItemValue::item),
						DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY)
								.forGetter(stack -> stack.item().getComponentsPatch())
				).apply(instance, (item, patch) -> {
					item.applyComponents(patch);
					return new Ingredient.ItemValue(item);
				})
		);
	}
}
