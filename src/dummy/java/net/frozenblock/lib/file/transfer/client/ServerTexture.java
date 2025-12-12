package net.frozenblock.lib.file.transfer.client;

import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;

public class ServerTexture extends SimpleTexture {
	public ServerTexture(ResourceLocation location) {
		super(location);
		throw new AssertionError();
	}

	public void updateReferenceTime() {
		throw new AssertionError();
	}
}
