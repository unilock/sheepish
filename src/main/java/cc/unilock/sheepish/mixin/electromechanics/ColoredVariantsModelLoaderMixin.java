package cc.unilock.sheepish.mixin.electromechanics;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(targets = "com/chyzman/electromechanics/client/ColoredVariantsModelLoader")
@Pseudo
public class ColoredVariantsModelLoaderMixin {
	@Redirect(method = "resolveBlockStates", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/Object;)V"))
	private void resolveBlockStates$cancelPrintln(PrintStream instance, Object x) {
		// NO-OP
	}
}
