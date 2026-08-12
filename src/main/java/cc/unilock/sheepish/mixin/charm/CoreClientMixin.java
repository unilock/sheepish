package cc.unilock.sheepish.mixin.charm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import svenhjol.charm.charmony.Feature;
import svenhjol.charm.charmony.client.ClientFeature;
import svenhjol.charm.charmony.client.ClientLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.custom_recipes.CustomRecipesClient;

import java.util.List;

@Mixin(targets = "svenhjol/charm/feature/core/CoreClient")
@Pseudo
public class CoreClientMixin extends ClientFeature {
	public CoreClientMixin(ClientLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	/**
	 * @author unilock
	 * @reason exclude CustomWoodClient
	 */
	@Overwrite
	public List<? extends ChildFeature<? extends Feature>> children() {
		return List.of(new CustomRecipesClient(this.loader()));
	}
}
