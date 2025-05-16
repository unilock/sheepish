package cc.unilock.sheepish.mixin.affinity;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import io.wispforest.affinity.misc.MixinHooks;
import io.wispforest.affinity.misc.potion.PotionMixture;
import io.wispforest.affinity.misc.quack.ExtendedPotionContentsComponent;
import io.wispforest.affinity.object.AffinityStatusEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

@Mixin(PotionContents.class)
public class PotionContentsMixin implements ExtendedPotionContentsComponent {
    @Unique
    private WeakReference<ItemStack> affinity$stackRef;

    @Override
    public void affinity$attackStack(ItemStack stack) {
        affinity$stackRef = new WeakReference<>(stack);
    }

    @Inject(method = "addPotionTooltip(Ljava/util/function/Consumer;FF)V", at = @At("HEAD"))
    private void storeStack(Consumer<Component> textConsumer, float durationMultiplier, float tickRate, CallbackInfo ci) {
        ItemStack stack = affinity$stackRef == null ? null : affinity$stackRef.get();

        if (stack != null) MixinHooks.POTION_CONTENTS_COMPONENT_STACK.set(stack);
    }

    @Inject(method = "addPotionTooltip(Ljava/util/function/Consumer;FF)V", at = @At("TAIL"))
    private void releaseStack(Consumer<Component> textConsumer, float durationMultiplier, float tickRate, CallbackInfo ci) {
        MixinHooks.POTION_CONTENTS_COMPONENT_STACK.remove();
    }

    @ModifyArg(method = "addPotionTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;", ordinal = 1), index = 1)
    private static Object[] addLengthMultiplier(Object[] args, @Local(argsOnly = true, ordinal = 0) float durationMultiplier, @Local(argsOnly = true, ordinal = 1) float tickRate, @Local MobEffectInstance effectInst) {
        if (!(args[1] instanceof PlainTextContents literal)) {
            return args;
        }
        String durationText = literal.text();

        var stack = MixinHooks.POTION_CONTENTS_COMPONENT_STACK.get();

        if (stack == null) {
            return args;
        }

        if (stack.has(PotionMixture.EXTEND_DURATION_BY)) {
            float extendBy = stack.get(PotionMixture.EXTEND_DURATION_BY);

            args[1] = durationText + " + " + StringUtil.formatTickDuration((int) (effectInst.getDuration() * durationMultiplier * (extendBy - 1)), tickRate);
        }

        return args;
    }

    @Inject(method = "addPotionTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V", at = @At("TAIL"))
    private static void addFuniFlightText(Iterable<MobEffectInstance> effects, Consumer<Component> tooltip, float durationMultiplier, float tickRate, CallbackInfo ci, @Local List<Pair<Attribute, AttributeModifier>> attributeModifiers) {
        if (StreamSupport.stream(effects.spliterator(), false).noneMatch(statusEffectInstance -> statusEffectInstance.getEffect().value() == AffinityStatusEffects.FLIGHT)) {
            return;
        }

        if (attributeModifiers.isEmpty()) {
            tooltip.accept(Component.empty());
            tooltip.accept((Component.translatable("potion.whenDrank")).withStyle(ChatFormatting.DARK_PURPLE));
        }
        tooltip.accept(Component.translatable("effect.affinity.gravity_modifier").withStyle(ChatFormatting.BLUE));
    }
}