package svenhjol.charm.feature.core.custom_recipes;

import svenhjol.charm.charmony.client.ClientFeature;
import svenhjol.charm.charmony.client.ClientLoader;
import svenhjol.charm.charmony.feature.ChildFeature;
import svenhjol.charm.feature.core.CoreClient;

public final class CustomRecipesClient extends ClientFeature implements ChildFeature<CoreClient> {
	public CustomRecipesClient(ClientLoader loader) {
		super(loader);
		throw new AssertionError();
	}
}
