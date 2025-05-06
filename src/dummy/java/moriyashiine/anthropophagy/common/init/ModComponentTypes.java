package moriyashiine.anthropophagy.common.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class ModComponentTypes {
	public static final DataComponentType<Boolean> FROM_PLAYER = new DataComponentType.Builder<Boolean>().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build();
	public static final DataComponentType<String> OWNER_NAME = new DataComponentType.Builder<String>().persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8).build();
}
