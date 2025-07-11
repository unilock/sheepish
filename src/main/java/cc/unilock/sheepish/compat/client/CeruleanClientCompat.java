package cc.unilock.sheepish.compat.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import org.joml.Matrix4f;

public class CeruleanClientCompat {
	private static final ResourceLocation DREAMSCAPE = ResourceLocation.fromNamespaceAndPath("cerulean", "dreamscape");
	private static final ResourceLocation SKIES = ResourceLocation.fromNamespaceAndPath("cerulean", "skies");

	public static void init() {
		IEventBus modBus = ModList.get().getModContainerById("cerulean").orElseThrow().getEventBus();

		modBus.addListener(RegisterDimensionSpecialEffectsEvent.class, event -> {
			event.register(DREAMSCAPE, new DimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NORMAL, false, false) {
				@Override
				public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
					return fogColor;
				}

				@Override
				public boolean isFoggyAt(int x, int y) {
					return false;
				}

				@Override
				public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
					return true;
				}

				@Override
				public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
					return true;
				}

				@Override
				public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
					return true;
				}
			});
			event.register(SKIES, new DimensionSpecialEffects(Float.NaN, false, DimensionSpecialEffects.SkyType.NORMAL, true, false) {
				@Override
				public Vec3 getBrightnessDependentFogColor(Vec3 fogColor, float brightness) {
					return fogColor;
				}

				@Override
				public boolean isFoggyAt(int x, int y) {
					return false;
				}

				@Override
				public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f modelViewMatrix, Matrix4f projectionMatrix) {
					return true;
				}

				@Override
				public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
					return true;
				}

				@Override
				public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
					return true;
				}
			});
		});
	}
}
