package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.adjuster.MixinAnnotationAdjusterRegistrar;
import com.bawnorton.mixinsquared.canceller.MixinCancellerRegistrar;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

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
		ModFileInfo modFile = LoadingModList.get().getModFileById(id);

		if ("emi".equals(id)) {
			return modFile != null && CONFIG.emiHacks.value();
		}
		if ("fabric_transfer_api_v1".equals(id)) {
			return modFile != null && CONFIG.fabricTransferApiHacks.value();
		}
		if ("trickster".equals(id)) {
			// Trickster 2.0.0-beta.34 is the latest version that works without our fixes
			// Don't bother accounting for older versions. I'm not JiJ'ing FlexVer
			return modFile != null && !modFile.versionString().equals("2.0.0-beta.34");
		}

		return modFile != null;
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
