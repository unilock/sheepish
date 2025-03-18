package cc.unilock.eternaldeer;

import cc.unilock.eternaldeer.mixin.EternalDeerMixinAnnotationAdjuster;
import com.bawnorton.mixinsquared.adjuster.MixinAnnotationAdjusterRegistrar;
import com.bawnorton.mixinsquared.canceller.MixinCancellerRegistrar;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class EternalDeerMixin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {
		MixinAnnotationAdjusterRegistrar.register(new EternalDeerMixinAnnotationAdjuster());
		MixinCancellerRegistrar.register(new EternalDeerMixinCanceller());
	}

	@Override
	public String getRefMapperConfig() {
		// NO-OP
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		String id = mixinClassName.split("\\.")[4];

		return FabricLoader.getInstance().isModLoaded(id);
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// NO-OP
	}

	@Override
	public List<String> getMixins() {
		// NO-OP
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}
}
