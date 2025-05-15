package cc.unilock.eternaldeer;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class EternalDeerMixinCanceller implements MixinCanceller {
	private static final Set<String> CANCEL = Set.of(
			"de.maxhenkel.wiretap.mixin.AnvilMenuMixin",
			"fmt.cerulean.mixin.MixinEntity",
			"gay.pridecraft.joy.mixin.minecraft.MixinLivingEntity",
			"io.wispforest.affinity.mixin.VillagerClothingFeatureRendererAccessor",
			"io.wispforest.affinity.mixin.client.ArmorFeatureRendererMixin",
			"io.wispforest.affinity.mixin.client.BufferBuilderStorageMixin",
			"io.wispforest.affinity.mixin.client.ItemFrameEntityRendererMixin",
			"io.wispforest.affinity.mixin.client.sodium.DefaultMaterialsMixin",
			"io.wispforest.affinity.mixin.client.sodium.DefaultTerrainRenderPassesMixin",
			"io.wispforest.affinity.mixin.client.sodium.SodiumWorldRendererMixin",
			"nourl.mythicmetals.mixin.ElytraFeatureRendererMixin"
	);

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName) || mixinClassName.startsWith("net.joefoxe.hexerei.mixin.light.");
	}
}
