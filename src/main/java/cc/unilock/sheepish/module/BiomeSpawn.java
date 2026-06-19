package cc.unilock.sheepish.module;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

public class BiomeSpawn {
	private static final int MAX_RADIUS = 10000;
	private static final int SEARCH_RADIUS = 2048;
	private static final int ATTEMPTS = 24;

	private Either<Holder<Biome>, TagKey<Biome>> biome;

	@SubscribeEvent
	public void serverStarted(ServerStartedEvent event) {
		if (!CONFIG.biomeSpawn.value().isBlank()) {
			String cfg = CONFIG.biomeSpawn.value();

			boolean isTag = cfg.startsWith("#");
			cfg = isTag ? cfg.substring(1) : cfg;

			ResourceLocation rl = ResourceLocation.tryParse(cfg);
			if (rl == null) {
				throw new RuntimeException("Failed to parse ResourceLocation: "+ cfg);
			}

			this.biome = isTag ? Either.right(TagKey.create(Registries.BIOME, rl)) : Either.left(event.getServer().registryAccess().holderOrThrow(ResourceKey.create(Registries.BIOME, rl)));

			ServerLevel overworld = event.getServer().overworld();
			BlockPos oldSpawnPos = overworld.getSharedSpawnPos();
			BlockPos origin = BlockPos.ZERO.above(64);
			if (!isValidBiome(overworld, oldSpawnPos)) {
				for (int attempt = 0; attempt < ATTEMPTS; attempt++) {
					Pair<BlockPos, Holder<Biome>> location = overworld.findClosestBiome3d(this::isValidBiome, origin, SEARCH_RADIUS, 32, 64);
					if (location != null) {
						BlockPos biomePos = location.getFirst();
						if (Math.abs(biomePos.getX()) <= MAX_RADIUS && Math.abs(biomePos.getZ()) <= MAX_RADIUS) {
							BlockPos newSpawnPos = findValidPos(overworld, biomePos);
							if (newSpawnPos != null) {
								overworld.setDefaultSpawnPos(newSpawnPos, overworld.getSharedSpawnAngle());
								return;
							}
						}
					}
				}
			}
		}
	}

	private boolean isValidBiome(ServerLevel level, BlockPos pos) {
		return isValidBiome(level.getBiome(pos));
	}

	private boolean isValidBiome(Holder<Biome> holder) {
		return biome.map(
				holder::is,
				holder::is
		);
	}

	private boolean isValidBlock(ServerLevel level, BlockPos pos) {
		return level.getFluidState(pos.below()).isEmpty() && level.getFluidState(pos).isEmpty();
	}

	private BlockPos findValidPos(ServerLevel level, BlockPos center) {
		for (int r = 0; r <= 32; r += 8) {
			for (int dx = -r; dx <= r; dx += 8) {
				for (int dz = -r; dz <= r; dz += 8) {
					if (Math.max(Math.abs(dx), Math.abs(dz)) == r) {
						int x = center.getX() + dx;
						int z = center.getZ() + dz;
						level.getChunk(x >> 4, z >> 4);
						int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
						BlockPos validPos = new BlockPos(x, y, z);
						if (isValidBlock(level, validPos)) {
							return validPos;
						}
					}
				}
			}
		}

		return null;
	}
}
