package svenhjol.charm.feature.core.custom_advancements;

import svenhjol.charm.charmony.common.CommonFeature;
import svenhjol.charm.charmony.common.CommonLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.Core;

public final class CustomAdvancements extends CommonFeature implements ChildFeature<Core> {
	public CustomAdvancements(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}
}
