package cc.unilock.sheepish.mixin.charm;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.neoforged.fml.loading.LoadingModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

@Mixin(targets = "svenhjol/charm/fabric/ClientInitializer")
@Pseudo
public class ClientInitializerMixin {
	@WrapMethod(method = "onInitializeClient")
	private void onInitializeClient(Operation<Void> original) {
		if (LoadingModList.get().getModFileById("strange") == null) {
			original.call();
		}
	}
}
