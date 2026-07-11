package cc.unilock.sheepish.mixin.naturesaura;

import cc.unilock.sheepish.compat.NoMansLandCompat;
import cc.unilock.sheepish.compat.WilderWildCompat;
import de.ellpeck.naturesaura.blocks.BlockOakGenerator;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockOakGenerator.class, remap = false)
public class BlockOakGeneratorMixin {
	@Inject(method = "getReplacement", at = @At("HEAD"), cancellable = true)
	private static void getReplacement(Holder<ConfiguredFeature<?, ?>> holder, CallbackInfoReturnable<ResourceKey<ConfiguredFeature<?, ?>>> cir) {
		if (holder != null) {
			ResourceKey<ConfiguredFeature<?, ?>> feature = holder.getKey();

			if (feature == NoMansLandCompat.FANCY_OAK_APPLE_01) {
				cir.setReturnValue(NoMansLandCompat.OAK_APPLE_01);
			} else if (feature == NoMansLandCompat.FANCY_OAK_APPLE_05) {
				cir.setReturnValue(NoMansLandCompat.OAK_APPLE_05);
			} else if (feature == WilderWildCompat.FANCY_OAK || feature == WilderWildCompat.FANCY_OAK_BEES) {
				cir.setReturnValue(WilderWildCompat.OAK);
			} else if (feature == WilderWildCompat.FANCY_OAK_BEES_0004) {
				cir.setReturnValue(WilderWildCompat.OAK_BEES_0004);
			} else if (feature == WilderWildCompat.FANCY_OAK_BEES_025) {
				// TODO...?
				cir.setReturnValue(TreeFeatures.OAK);
			}
		}
	}
}
