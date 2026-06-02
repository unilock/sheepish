package dev.enjarai.trickster.cca;

import net.minecraft.world.level.ChunkPos;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class PinnedChunksComponent implements ServerTickingComponent {
	public boolean isPinned(ChunkPos pos) {
		throw new AssertionError();
	}
}
