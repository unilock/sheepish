package cc.unilock.eternaldeer.mixin.electromechanics;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com/chyzman/electromechanics/client/ColoredVariantsModelLoader")
@Pseudo
public class ColoredVariantsModelLoaderMixin {
	@Redirect(method = "resolveBlockStates", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println()V"))
	private static void resolveBlockStates$cancelSystemOutPrintln() {
		// NO-OP
	}
}
