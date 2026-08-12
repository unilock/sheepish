package svenhjol.charm.charmony.client;

import svenhjol.charm.charmony.Loader;

public class ClientLoader extends Loader<ClientFeature> {
	protected ClientLoader(String id) {
		super(id);
		throw new AssertionError();
	}

	public ClientRegistry registry() {
		throw new AssertionError();
	}
}
