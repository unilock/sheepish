package cc.unilock.eternaldeer;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.adjuster.tools.AdjustableInjectNode;
import com.bawnorton.mixinsquared.adjuster.tools.AdjustableModifyVariableNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

public class EternalDeerMixinAnnotationAdjuster implements MixinAnnotationAdjuster {
	@Override
	public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotationNode) {
		if ("fmt.cerulean.mixin.client.MixinMinecraftClient".equals(mixinClassName) && annotationNode.is(Inject.class)) {
			AdjustableInjectNode wrap = annotationNode.as(AdjustableInjectNode.class);

			if (wrap.getMethod().getFirst().equals("handleBlockBreaking")) {
				return null;
			}
		}

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && annotationNode.is(Inject.class)) {
			AdjustableInjectNode wrap = annotationNode.as(AdjustableInjectNode.class);

			if (wrap.getMethod().getFirst().equals("tick")) {
				return null;
			}
		}

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && annotationNode.is(ModifyVariable.class)) {
			AdjustableModifyVariableNode wrap = annotationNode.as(AdjustableModifyVariableNode.class);

			if (wrap.getMethod().getFirst().equals("updateWaterState")) {
				return null;
			}
		}

		return annotationNode;
	}
}
