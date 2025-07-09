package cc.unilock.sheepish.mixin.specter_serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.mojang.serialization.JsonOps;
import dev.spiritstudios.specter.impl.serialization.SpecterSerialization;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.BiConsumer;

@Mixin(Language.class)
public class LanguageMixin {
	@WrapOperation(method = "loadFromJson(Ljava/io/InputStream;Ljava/util/function/BiConsumer;Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/GsonHelper;convertToString(Lcom/google/gson/JsonElement;Ljava/lang/String;)Ljava/lang/String;"))
	private static String load(JsonElement element, String name, Operation<String> original, @Local(argsOnly = true, ordinal = 0) BiConsumer<String, String> entryConsumer, @Share("skip") LocalBooleanRef skip) {
		if (element.isJsonPrimitive()) {
			skip.set(false);
			return original.call(element, name);
		}

		Component text = ComponentSerialization.CODEC.parse(JsonOps.INSTANCE, element).getOrThrow(JsonParseException::new);
		SpecterSerialization.TEXT_TRANSLATIONS_BUILDER.get().put(name, text);

		skip.set(true);
		return "";
	}

	@WrapWithCondition(method = "loadFromJson(Ljava/io/InputStream;Ljava/util/function/BiConsumer;Ljava/util/function/BiConsumer;)V", at = @At(value = "INVOKE", target = "Ljava/util/function/BiConsumer;accept(Ljava/lang/Object;Ljava/lang/Object;)V"))
	private static <T, U> boolean skip(BiConsumer<T, U> instance, T t, U u, @Share("skip") LocalBooleanRef skip) {
		return !skip.get();
	}
}
