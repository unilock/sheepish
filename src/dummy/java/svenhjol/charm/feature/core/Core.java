package svenhjol.charm.feature.core;

import svenhjol.charm.charmony.Feature;
import svenhjol.charm.charmony.common.CommonFeature;
import svenhjol.charm.charmony.common.CommonLoader;
import svenhjol.charm.charmony.feature.ChildFeature;

import java.util.List;

public final class Core extends CommonFeature {
	public Core(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	public List<? extends ChildFeature<? extends Feature>> children() {
		throw new AssertionError();
	}
}
