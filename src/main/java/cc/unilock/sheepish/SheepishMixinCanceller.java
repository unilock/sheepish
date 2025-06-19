package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class SheepishMixinCanceller implements MixinCanceller {
	private static final Set<String> CANCEL = Set.of(
			"com.terraformersmc.cinderscapes.mixin.MixinAlterGroundTreeDecorator",
			"de.maxhenkel.wiretap.mixin.AnvilMenuMixin",
			"fmt.cerulean.mixin.MixinEntity",
			"gay.pridecraft.joy.mixin.minecraft.MixinLivingEntity",
			"io.wispforest.affinity.mixin.VillagerClothingFeatureRendererAccessor",
			"io.wispforest.affinity.mixin.client.ArmorFeatureRendererMixin",
			"io.wispforest.affinity.mixin.client.BufferBuilderStorageMixin",
			"io.wispforest.affinity.mixin.client.ItemFrameEntityRendererMixin",
			"net.frozenblock.lib.block.mixin.friction.LivingEntityMixin",
			"net.frozenblock.lib.recipe.mixin.ItemValueMixin",
			"net.frozenblock.lib.worldgen.biome.mixin.RegistryDataLoaderMixin",
			"net.frozenblock.wilderwild.mixin.block.mesoglea.LivingEntityMixin",
			"net.modfest.fireblanket.mixin.annoyances.MixinUtil",
			"org.quiltmc.qsl.frozenblock.core.registry.mixin.MappedRegistryMixin"
	);

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName) || mixinClassName.startsWith("net.joefoxe.hexerei.mixin.light.") || mixinClassName.equals("net.modfest.fireblanket.mixin.client.color.") || mixinClassName.startsWith("net.modfest.fireblanket.mixin.mods.create.");
	}
}
