package svenhjol.charm.feature.core.custom_pistons;

import svenhjol.charm.charmony.common.CommonFeature;
import svenhjol.charm.charmony.common.CommonLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.Core;

public final class CustomPistons extends CommonFeature implements ChildFeature<Core> {
	public CustomPistons(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}
}
