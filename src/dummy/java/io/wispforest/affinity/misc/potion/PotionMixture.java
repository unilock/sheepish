package io.wispforest.affinity.misc.potion;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class PotionMixture {
	public static final DataComponentType<Float> EXTEND_DURATION_BY = DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build();
}
