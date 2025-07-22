package cc.unilock.sheepish.mixin.fabric_resource_loader_v0;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.impl.resource.loader.FabricResourcePackProfile;
import net.minecraft.client.Options;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Options.class)
public class OptionsMixin {
	@WrapOperation(method = "updateResourcePacks", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/Pack;isHidden()Z"))
	private boolean updateResourcePacks$isHidden(Pack instance, Operation<Boolean> original) {
		return original.call(instance) || ((FabricResourcePackProfile) instance).fabric_isHidden();
	}
}
