package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

public class SheepishMixinCanceller implements MixinCanceller {
	private static final HashSet<String> CANCEL = Sets.newHashSet(
			"com.terraformersmc.cinderscapes.mixin.MixinAlterGroundTreeDecorator",

			"de.maxhenkel.wiretap.mixin.AnvilMenuMixin",

			"dev.spiritstudios.specter.mixin.serialization.LanguageMixin",

			"fmt.cerulean.mixin.MixinEntity",

			"gay.pridecraft.joy.mixin.minecraft.MixinLivingEntity",

			"io.wispforest.affinity.mixin.client.ItemFrameEntityRendererMixin",

			"net.frozenblock.lib.block.mixin.friction.LivingEntityMixin",
			"net.frozenblock.lib.cape.mixin.client.CapeLayerMixin",
			"net.frozenblock.lib.cape.mixin.client.ElytraLayerMixin",
//			"net.frozenblock.lib.event.mixin.BuiltInRegistriesMixin",
			"net.frozenblock.lib.item.mixin.axe.AxeItemMixin",
			"net.frozenblock.lib.file.mixin.transfer.client.TextureManagerMixin",
			"net.frozenblock.lib.recipe.mixin.ItemValueMixin",
			"net.frozenblock.lib.resource_pack.mixin.client.PackRepositoryMixin",
//			"net.frozenblock.lib.worldgen.biome.mixin.RegistryDataLoaderMixin",
			"org.quiltmc.qsl.frozenblock.core.registry.mixin.MappedRegistryMixin",

			"net.frozenblock.wilderwild.mixin.block.mesoglea.LivingEntityMixin",
			"net.frozenblock.wilderwild.mixin.client.wind.SporeBlossomAirProviderMixin",
			"net.frozenblock.wilderwild.mixin.entity.penguin.BlocksMixin",
			"net.frozenblock.wilderwild.mixin.snowlogging.BlockItemMixin",

			"net.modfest.fireblanket.mixin.annoyances.MixinUtil"
	);
	static {
		if (CONFIG.disableSplashes.value()) {
			CANCEL.add("com.teamabnormals.blueprint.core.mixin.client.SplashManagerMixin");
			CANCEL.add("net.frozenblock.lib.menu.mixin.client.SplashManagerMixin");
		}
	}

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName)
				|| mixinClassName.startsWith("net.joefoxe.hexerei.mixin.light.")
				|| mixinClassName.startsWith("net.modfest.fireblanket.mixin.mods.create.");
	}
}
