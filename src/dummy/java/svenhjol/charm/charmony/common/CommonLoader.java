package svenhjol.charm.charmony.common;

import svenhjol.charm.charmony.Loader;

public class CommonLoader extends Loader<CommonFeature> {
	protected CommonLoader(String id) {
		super(id);
		throw new AssertionError();
	}

	public CommonRegistry registry() {
		throw new AssertionError();
	}
}
