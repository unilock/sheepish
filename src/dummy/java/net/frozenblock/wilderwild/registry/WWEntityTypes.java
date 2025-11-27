package net.frozenblock.wilderwild.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class WWEntityTypes {
	public static final EntityType<?> PENGUIN = EntityType.Builder.createNothing(MobCategory.CREATURE).build("wilderwild:penguin");
}
