package cc.unilock.sheepish;

import com.bawnorton.mixinsquared.adjuster.tools.AdjustableAnnotationNode;
import com.bawnorton.mixinsquared.adjuster.tools.AdjustableWrapOperationNode;
import com.bawnorton.mixinsquared.api.MixinAnnotationAdjuster;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class SheepishMixinAnnotationAdjuster implements MixinAnnotationAdjuster {
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

		if ("fmt.cerulean.mixin.client.MixinMinecraftClient".equals(mixinClassName) && "cerulean$starwellBreaking".equals(handlerNode.name)) {
			return null;
		}

		if ("folk.sisby.tinkerers_smithing.mixin.ItemStackMixin".equals(mixinClassName) && ("brokenNoDamage".equals(handlerNode.name) || "dontBreakDecrementKeepers".equals(handlerNode.name) || "dontBreakResetKeepers".equals(handlerNode.name))) {
			return null;
		}

		if ("io.wispforest.affinity.mixin.EntityMixin".equals(mixinClassName) && ("invokeFadeTickEvent".equals(handlerNode.name) || "updateFadeState".equals(handlerNode.name))) {
			return null;
		}

		if ("moriyashiine.anthropophagy.mixin.AbstractFurnaceBlockEntityMixin".equals(mixinClassName) && "anthropophagy$persistFleshOwner".equals(handlerNode.name)) {
			return null;
		}

		if ("net.frozenblock.lib.item.mixin.shovel.ShovelItemMixin".equals(mixinClassName) && "frozenlib$removeOtherBehaviorsB".equals(handlerNode.name)) {
			return null;
		}

		if ("net.frozenblock.wilderwild.mixin.block.block_break.ServerPlayerGameModeMixin".equals(mixinClassName) && "wilderWild$destroyBlockB".equals(handlerNode.name)) {
			return null;
		}

		if ("net.frozenblock.wilderwild.mixin.client.block_break.MultiPlayerGameModeMixin".equals(mixinClassName) && "wilderWild$destroyBlockB".equals(handlerNode.name)) {
			return null;
		}

		if ("org.ladysnake.satin.mixin.client.gl.JsonEffectGlShaderMixin".equals(mixinClassName) && "constructProgramIdentifier".equals(handlerNode.name)) {
			return null;
		}

		if ("net.modfest.fireblanket.mixin.zstd.MixinPersistentState".equals(mixinClassName)) {
			AdjustableWrapOperationNode wrap = annotationNode.as(AdjustableWrapOperationNode.class);
			wrap.applyRefmap();

			return wrap.withMethod(methods -> {
				methods.set(0, "lambda$save$0");
				return methods;
			})
			.withAt(ats -> {
				ats.set(0, ats.getFirst().withTarget(s -> "Lnet/neoforged/neoforge/common/IOUtilities;writeNbtCompressed(Lnet/minecraft/nbt/CompoundTag;Ljava/nio/file/Path;)V"));
				return ats;
			});
		}

		if ("xyz.nucleoid.fantasy.mixin.ServerWorldMixin".equals(mixinClassName) && "dontSendRainPacketsToAllWorlds".equals(handlerNode.name)) {
			return null;
		}

		return annotationNode;
	}
}
