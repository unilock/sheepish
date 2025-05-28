package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class SheepishMixinCanceller implements MixinCanceller {
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
			"net.frozenblock.lib.block.mixin.friction.LivingEntityMixin",
			"net.frozenblock.lib.feature_flag.mixin.FeatureFlagBuilderMixin",
			"net.frozenblock.lib.recipe.mixin.ItemValueMixin",
			"net.frozenblock.lib.worldgen.biome.mixin.RegistryDataLoaderMixin",
			"net.frozenblock.wilderwild.mixin.block.mesoglea.LivingEntityMixin",
			"net.modfest.fireblanket.mixin.annoyances.MixinUtil",
			"net.modfest.fireblanket.mixin.entity_ticking.MixinEntity",
			"net.modfest.fireblanket.mixin.entity_ticking.MixinPlayerLookup",
			"net.modfest.fireblanket.mixin.mods.mythicmetals.MixinCarmotShield",
			"nourl.mythicmetals.mixin.ElytraFeatureRendererMixin",
			"org.quiltmc.qsl.frozenblock.core.registry.mixin.MappedRegistryMixin"
	);

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName) || mixinClassName.startsWith("net.joefoxe.hexerei.mixin.light.") || mixinClassName.equals("net.modfest.fireblanket.mixin.client.color.") || mixinClassName.startsWith("net.modfest.fireblanket.mixin.mods.create.");
	}
}
