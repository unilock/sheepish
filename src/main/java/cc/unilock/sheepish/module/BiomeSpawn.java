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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

public class BiomeSpawn {
	private static final int MAX_RADIUS = 10000;
	private static final int SEARCH_RADIUS = 2048;
	private static final int ATTEMPTS = 24;

	private Either<Holder<Biome>, TagKey<Biome>> biome;
	private Either<Holder<Block>, TagKey<Block>> block;
	private boolean skipBlockCheck;

	@SubscribeEvent
	public void serverStarted(ServerStartedEvent event) {
		if (!CONFIG.biomeSpawn.value().isBlank()) {
			String cfgBiome = CONFIG.biomeSpawn.value();
			boolean cfgBiomeIsTag = cfgBiome.startsWith("#");
			ResourceLocation rlBiome = ResourceLocation.tryParse(cfgBiomeIsTag ? cfgBiome.substring(1) : cfgBiome);
			this.biome = cfgBiomeIsTag ? Either.right(TagKey.create(Registries.BIOME, rlBiome)) : Either.left(event.getServer().registryAccess().holderOrThrow(ResourceKey.create(Registries.BIOME, rlBiome)));

			if (!CONFIG.blockSpawn.value().isBlank()) {
				this.skipBlockCheck = false;
				String cfgBlock = CONFIG.blockSpawn.value();
				boolean cfgBlockIsTag = cfgBlock.startsWith("#");
				ResourceLocation rlBlock = ResourceLocation.tryParse(cfgBlockIsTag ? cfgBlock.substring(1) : cfgBlock);
				this.block = cfgBlockIsTag ? Either.right(TagKey.create(Registries.BLOCK, rlBlock)) : Either.left(event.getServer().registryAccess().holderOrThrow(ResourceKey.create(Registries.BLOCK, rlBlock)));
			} else {
				this.skipBlockCheck = true;
			}

			ServerLevel overworld = event.getServer().overworld();
			BlockPos oldSpawnPos = overworld.getSharedSpawnPos();
			if (!isValidBiome(overworld, oldSpawnPos)) {
				for (int attempt = 0; attempt < ATTEMPTS; attempt++) {
					Pair<BlockPos, Holder<Biome>> location = overworld.findClosestBiome3d(holder -> biome.map(holder::is, holder::is), oldSpawnPos, SEARCH_RADIUS, 32, 64);
					if (location != null) {
						BlockPos biomePos = location.getFirst();
						if (Math.abs(biomePos.getX()) <= (Math.abs(oldSpawnPos.getX()) - MAX_RADIUS) && Math.abs(biomePos.getZ()) <= (Math.abs(oldSpawnPos.getZ()) - MAX_RADIUS)) {
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
		return biome.map(
				holder -> level.getBiome(pos).is(holder),
				tag -> level.getBiome(pos).is(tag)
		);
	}

	private boolean isValidBlock(ServerLevel level, BlockPos pos) {
		BlockPos ground = pos.below();
		return block.map(
				holder -> level.getBlockState(ground).is(holder),
				tag -> level.getBlockState(ground).is(tag)
		) && level.getFluidState(ground).isEmpty() && level.getFluidState(pos).isEmpty();
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
						if (skipBlockCheck || isValidBlock(level, validPos)) {
							return validPos;
						}
					}
				}
			}
		}

		return null;
	}
}
