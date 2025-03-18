package cc.unilock.eternaldeer;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class EternalDeerMixinCanceller implements MixinCanceller {
	private static final Set<String> CANCEL = Set.of(
			"fmt.cerulean.mixin.MixinEntity",
			"nourl.mythicmetals.mixin.ElytraFeatureRendererMixin"
	);

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName);
	}
}
