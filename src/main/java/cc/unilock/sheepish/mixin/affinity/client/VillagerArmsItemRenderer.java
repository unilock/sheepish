package cc.unilock.sheepish.mixin.affinity.client;

import cc.unilock.sheepish.mixin.affinity.client.accessor.VillagerProfessionLayerAccessor;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "io/wispforest/affinity/client/render/item/VillagerArmsItemRenderer")
@Pseudo
public class VillagerArmsItemRenderer {
	@Redirect(method = "renderArms(Lnet/minecraft/world/entity/npc/VillagerData;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lio/wispforest/affinity/mixin/VillagerClothingFeatureRendererAccessor;affinity$LevelToIdMap()Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;"))
	private static Int2ObjectMap<ResourceLocation> renderArms$getLevelToIdMap() {
		return VillagerProfessionLayerAccessor.getLEVEL_LOCATIONS();
	}
}
