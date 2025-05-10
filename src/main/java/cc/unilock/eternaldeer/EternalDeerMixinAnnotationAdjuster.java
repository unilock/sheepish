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

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && ("invokeFadeTickEvent".equals(handlerNode.name) || "updateFadeState".equals(handlerNode.name))) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.client.ItemRendererMixin".equals(mixinClassName) && "punchAHoleIntoYourInventory".equals(handlerNode.name)) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.client.WorldRendererMixin".equals(mixinClassName) && ("initSkyBuffer".equals(handlerNode.name) || "captureSky".equals(handlerNode.name) || "renderSkyStencilLayer".equals(handlerNode.name) || "drawSkyAfter".equals(handlerNode.name) || "drawSkyAfter_iris".equals(handlerNode.name))) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.client.sodium.DefaultTerrainRenderPassesMixin".equals(mixinClassName) && "punchAHoleIntoYourInventory".equals(handlerNode.name)) {
			return null;
		}

		if ("moriyashiine.anthropophagy.mixin.AbstractFurnaceBlockEntityMixin".equals(mixinClassName) && "anthropophagy$persistFleshOwner".equals(handlerNode.name)) {
			return null;
		}

		if ("xyz.nucleoid.fantasy.mixin.ServerWorldMixin".equals(mixinClassName) && "dontSendRainPacketsToAllWorlds".equals(handlerNode.name)) {
			return null;
		}

		return annotationNode;
	}
}
