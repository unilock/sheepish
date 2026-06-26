package cc.unilock.sheepish.mixin.caverns_and_chasms;

import com.teamabnormals.caverns_and_chasms.core.other.CCCreativeTabs;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CCCreativeTabs.class)
public class CCCreativeTabsMixin {
	@Redirect(method = "lambda$static$2", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;get()Ljava/lang/Object;"))
	private static Object lambda$getConfigValueA(ModConfigSpec.BooleanValue instance) {
		return true;
	}

	@Redirect(method = "lambda$static$112", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;get()Ljava/lang/Object;"))
	private static Object lambda$getConfigValueB(ModConfigSpec.BooleanValue instance) {
		return true;
	}
}
