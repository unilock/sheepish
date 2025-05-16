package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.adjuster.MixinAnnotationAdjusterRegistrar;
import com.bawnorton.mixinsquared.canceller.MixinCancellerRegistrar;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class SheepishMixin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {
		MixinAnnotationAdjusterRegistrar.register(new SheepishMixinAnnotationAdjuster());
		MixinCancellerRegistrar.register(new SheepishMixinCanceller());
	}

	@Override
	public String getRefMapperConfig() {
		return null; // NO-OP
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
		return null; // NO-OP
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
