package house.greenhouse.rapscallionsandrockhoppers.entity;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;

public record PenguinVariant(ResourceLocation texture, ResourceLocation surprisedTexture, SimpleWeightedRandomList<HolderSet<Biome>> biomes, PenguinSounds sounds, Optional<String> whenNamed, float size) {
	public record PenguinSounds(Optional<Holder<SoundEvent>> ambientSound, Optional<Holder<SoundEvent>> hurtSound, Optional<Holder<SoundEvent>> deathSound, Optional<Holder<SoundEvent>> waterJumpSound) {
	}
}
