package cc.unilock.sheepish.mixin.affinity.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.wispforest.affinity.object.AffinityBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "io/wispforest/affinity/client/AffinityClient")
@Pseudo
public class AffinityClientMixin {
	@WrapOperation(method = "assignBlockRenderLayers", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/blockrenderlayer/v1/BlockRenderLayerMap;putBlock(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/client/renderer/RenderType;)V"))
	private void assignBlockRenderLayers$cancelTheSky(BlockRenderLayerMap instance, Block block, RenderType renderType, Operation<Void> original) {
		if (AffinityBlocks.THE_SKY.equals(block)) return;

		original.call(instance, block, renderType);
	}

	@Redirect(method = "assignBlockRenderLayers", at = @At(value = "FIELD", target = "Lio/wispforest/affinity/client/render/SkyCaptureBuffer;SKY_STENCIL_LAYER:Lnet/minecraft/client/renderer/RenderType;", opcode = Opcodes.GETSTATIC))
	private static RenderType assignBlockRenderLayers$cancelTheSky() {
		return null;
	}
}
