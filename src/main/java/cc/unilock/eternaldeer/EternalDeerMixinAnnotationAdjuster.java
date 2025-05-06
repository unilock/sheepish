package cc.unilock.eternaldeer;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class EternalDeerMixinAnnotationAdjuster implements MixinAnnotationAdjuster {
	@Override
	public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotationNode) {
		if ("fmt.cerulean.mixin.client.MixinMinecraftClient".equals(mixinClassName) && "cerulean$starwellBreaking".equals(handlerNode.name)) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && "invokeFadeTickEvent".equals(handlerNode.name)) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && "updateFadeState".equals(handlerNode.name)) {
			return null;
		}

		if ("moriyashiine.anthropophagy.mixin.AbstractFurnaceBlockEntityMixin".equals(mixinClassName) && "anthropophagy$persistFleshOwner".equals(handlerNode.name)) {
			return null;
		}

		return annotationNode;
	}
}
