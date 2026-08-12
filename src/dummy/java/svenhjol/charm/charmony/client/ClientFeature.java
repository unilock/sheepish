package svenhjol.charm.charmony.client;

import svenhjol.charm.charmony.Feature;

public class ClientFeature extends Feature {
	public ClientFeature(ClientLoader loader) {
		super(loader);
		throw new AssertionError();
	}

	public ClientLoader loader() {
		throw new AssertionError();
	}

	public ClientRegistry registry() {
		throw new AssertionError();
	}
}

