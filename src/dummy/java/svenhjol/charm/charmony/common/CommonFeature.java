package svenhjol.charm.charmony.common;

import svenhjol.charm.charmony.Feature;

public class CommonFeature extends Feature {
	public CommonFeature(CommonLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	public CommonLoader loader() {
		throw new AssertionError();
	}

	public CommonRegistry registry() {
		throw new AssertionError();
	}
}
