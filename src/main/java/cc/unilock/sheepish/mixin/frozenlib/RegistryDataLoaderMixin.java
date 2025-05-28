package cc.unilock.sheepish.mixin.frozenlib;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.lib.worldgen.biome.api.FrozenGrassColorModifiers;
import net.frozenblock.lib.worldgen.biome.impl.BiomeInterface;
import net.frozenblock.lib.worldgen.biome.impl.FrozenGrassColorModifier;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = RegistryDataLoader.class, priority = 50)
public class RegistryDataLoaderMixin {
	@WrapOperation(
			//lambda$loadElementFromResource$13
			method = {"loadContentsFromNetwork", "lambda$loadElementFromResource$13"},
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/core/WritableRegistry;register(Lnet/minecraft/resources/ResourceKey;Ljava/lang/Object;Lnet/minecraft/core/RegistrationInfo;)Lnet/minecraft/core/Holder$Reference;"
			),
			require = 2
	)
	private static Holder.Reference frozenLib$appendBiomeIDFromNetwork(
			WritableRegistry instance, ResourceKey resourceKey, Object object, RegistrationInfo registrationInfo, Operation<Holder.Reference> original
	) {
		if (object instanceof BiomeInterface biomeInterface) {
			Optional<FrozenGrassColorModifier> optionalFrozenGrassColorModifier = FrozenGrassColorModifiers.getGrassColorModifier(resourceKey.location());
			optionalFrozenGrassColorModifier.ifPresent(biomeInterface::frozenLib$setFrozenGrassColorModifier);
		}
		return original.call(instance, resourceKey, object, registrationInfo);
	}
}
