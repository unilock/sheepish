package cc.unilock.sheepish.mixin.extradelight;

import com.lance5057.extradelight.workstations.chiller.ChillerBlockEntity;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ChillerBlockEntity.class, remap = false)
public class ChillerBlockEntityMixin {
	@WrapMethod(method = "testChillTime")
	private static boolean testChillTime(ChillerBlockEntity chiller, Operation<Boolean> original) {
		try {
			return original.call(chiller);
		} catch (NullPointerException ignored) {
			return false;
		}
	}
}
