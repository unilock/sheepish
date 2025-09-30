package net.frozenblock.trailiertales.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class BoatBannerModel extends EntityModel<Boat> {
	public BoatBannerModel(ModelPart root) {
		throw new AssertionError();
	}

	@Override
	public void setupAnim(Boat boat, float v, float v1, float v2, float v3, float v4) {
		throw new AssertionError();
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
		throw new AssertionError();
	}

	public void setRaft(boolean raft) {
		throw new AssertionError();
	}

	public void beforeRender(PoseStack matrices) {
		throw new AssertionError();
	}

	public void afterRender(PoseStack matrices) {
		throw new AssertionError();
	}

	public void renderFlag(
			PoseStack matrices,
			MultiBufferSource vertexConsumers,
			int light,
			int overlay,
			DyeColor dyeColor,
			BannerPatternLayers bannerPatternLayers
	) {
		throw new AssertionError();
	}
}
