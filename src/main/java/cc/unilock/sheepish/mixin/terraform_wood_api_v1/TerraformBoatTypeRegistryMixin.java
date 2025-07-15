package cc.unilock.sheepish.mixin.terraform_wood_api_v1;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.WritableRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "com/terraformersmc/terraform/boat/api/TerraformBoatTypeRegistry")
@Pseudo
public class TerraformBoatTypeRegistryMixin {
	@WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/event/registry/FabricRegistryBuilder;buildAndRegister()Lnet/minecraft/core/WritableRegistry;"))
	private static <T, R extends WritableRegistry<T>> R clinit$buildAndRegister$wrap(FabricRegistryBuilder<T, R> instance, Operation<R> original) {
		return original.call(instance.attribute(RegistryAttribute.SYNCED));
	}
}
