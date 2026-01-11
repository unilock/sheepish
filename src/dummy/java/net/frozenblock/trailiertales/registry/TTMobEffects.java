package net.frozenblock.trailiertales.registry;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class TTMobEffects {
	public static final Holder<MobEffect> HAUNT = new Holder<MobEffect>() {
		@Override
		public MobEffect value() {
			throw new AssertionError();
		}

		@Override
		public boolean isBound() {
			throw new AssertionError();
		}

		@Override
		public boolean is(ResourceLocation resourceLocation) {
			throw new AssertionError();
		}

		@Override
		public boolean is(ResourceKey<MobEffect> resourceKey) {
			throw new AssertionError();
		}

		@Override
		public boolean is(Predicate<ResourceKey<MobEffect>> predicate) {
			throw new AssertionError();
		}

		@Override
		public boolean is(TagKey<MobEffect> tagKey) {
			throw new AssertionError();
		}

		@Override
		public boolean is(Holder<MobEffect> holder) {
			throw new AssertionError();
		}

		@Override
		public Stream<TagKey<MobEffect>> tags() {
			throw new AssertionError();
		}

		@Override
		public Either<ResourceKey<MobEffect>, MobEffect> unwrap() {
			throw new AssertionError();
		}

		@Override
		public Optional<ResourceKey<MobEffect>> unwrapKey() {
			throw new AssertionError();
		}

		@Override
		public Kind kind() {
			throw new AssertionError();
		}

		@Override
		public boolean canSerializeIn(HolderOwner<MobEffect> holderOwner) {
			throw new AssertionError();
		}
	};
}
