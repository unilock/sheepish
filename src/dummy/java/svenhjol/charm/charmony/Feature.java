package svenhjol.charm.charmony;

import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.charmony.feature.Conditional;

import java.util.List;

public abstract class Feature implements Conditional {
	public Feature(Loader<? extends Feature> loader) {
		throw new AssertionError();
	}

	public Loader<? extends Feature> loader() {
		throw new AssertionError();
	}

	public abstract Registry registry();

	public boolean isEnabled() {
		throw new AssertionError();
	}

	public List<? extends ChildFeature<? extends Feature>> children() {
		throw new AssertionError();
	}
}
