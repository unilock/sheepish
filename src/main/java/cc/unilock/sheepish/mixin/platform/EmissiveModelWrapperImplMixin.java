package cc.unilock.sheepish.mixin.platform;

import com.blackgear.platform.client.v2.emissive.neoforge.EmissiveModelWrapperImpl;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(value = EmissiveModelWrapperImpl.class, remap = false)
public class EmissiveModelWrapperImplMixin {
	@WrapOperation(method = "lambda$bootstrap$0", at = @At(value = "INVOKE", target = "Ljava/util/Map$Entry;getValue()Ljava/lang/Object;"))
	private static Object lambda$getValue$wrap(Map.Entry<ModelResourceLocation, BakedModel> instance, Operation<BakedModel> original, @Local ModelResourceLocation id) {
		return "minecraft".equals(id.id().getNamespace()) ? original.call(instance) : null;
	}
}
