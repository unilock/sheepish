package cc.unilock.sheepish.mixin.charm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import svenhjol.charm.charmony.Feature;
import svenhjol.charm.charmony.common.CommonFeature;
import svenhjol.charm.charmony.common.CommonLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.custom_advancements.CustomAdvancements;
import svenhjol.charm.feature.core.custom_pistons.CustomPistons;
import svenhjol.charm.feature.core.custom_recipes.CustomRecipes;

import java.util.List;

@Mixin(targets = "svenhjol/charm/feature/core/Core")
@Pseudo
public class CoreMixin extends CommonFeature {
	public CoreMixin(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	/**
	 * @author unilock
	 * @reason exclude CustomWood
	 */
	@Overwrite
	public List<? extends ChildFeature<? extends Feature>> children() {
		return List.of(new CustomAdvancements(this.loader()), new CustomPistons(this.loader()), new CustomRecipes(this.loader()));
	}
}
