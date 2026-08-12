package svenhjol.charm.feature.core;

import svenhjol.charm.charmony.Feature;
import svenhjol.charm.charmony.client.ClientFeature;
import svenhjol.charm.charmony.client.ClientLoader;
import svenhjol.charm.charmony.feature.ChildFeature;

import java.util.List;

public final class CoreClient extends ClientFeature {
	public CoreClient(ClientLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	public List<? extends ChildFeature<? extends Feature>> children() {
		throw new AssertionError();
	}
}
