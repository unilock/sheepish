package cc.unilock.eternaldeer;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.adjuster.tools.AdjustableWrapOperationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class EternalDeerMixinAnnotationAdjuster implements MixinAnnotationAdjuster {
	@Override
	public AdjustableAnnotationNode adjust(List<String> targetClassNames, String mixinClassName, MethodNode handlerNode, AdjustableAnnotationNode annotationNode) {
		if ("com.chyzman.electromechanics.mixin.PistonHandlerMixin".equals(mixinClassName)) {
			// TODO: this does not appear to make slime slabs sticky
			if ("checkIfSlabsStick".equals(handlerNode.name) || "test".equals(handlerNode.name)) {
				AdjustableWrapOperationNode wrap = annotationNode.as(AdjustableWrapOperationNode.class);
				wrap.applyRefmap();

				return wrap.withAt(ats -> {
					ats.set(0, ats.getFirst().withTarget(s -> "Lnet/minecraft/world/level/block/state/BlockState;canStickTo(Lnet/minecraft/world/level/block/state/BlockState;)Z"));
					return ats;
				});
			}

			if ("isAdjacentBlockStuckExt".equals(handlerNode.name)) {
				return null;
			}
		}

		if ("com.chyzman.electromechanics.mixin.RedstoneWireBlockMixin".equals(mixinClassName) && annotationNode.is(WrapOperation.class)) {
			AdjustableWrapOperationNode wrap = annotationNode.as(AdjustableWrapOperationNode.class);

			if ("getRenderConnectionType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Lnet/minecraft/block/enums/WireConnection;".equals(wrap.getMethod().getFirst())) {
				return null;
			}
		}

		if ("de.dafuqs.spectrum.mixin.client.InGameHudMixin".equals(mixinClassName) && "spectrum$renderAzureDikeBar".equals(handlerNode.name)) {
			return null;
		}

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
