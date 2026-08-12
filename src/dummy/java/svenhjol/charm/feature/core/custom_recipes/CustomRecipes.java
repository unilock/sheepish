package svenhjol.charm.feature.core.custom_recipes;

import svenhjol.charm.charmony.common.CommonFeature;
import svenhjol.charm.charmony.common.CommonLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.Core;

public final class CustomRecipes extends CommonFeature implements ChildFeature<Core> {
	public CustomRecipes(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}
}
