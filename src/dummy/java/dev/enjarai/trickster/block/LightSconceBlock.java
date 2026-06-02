package dev.enjarai.trickster.block;

import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class LightSconceBlock extends LightBlock {
	public static final BooleanProperty HAS_LIGHT = BooleanProperty.create("has_light");

	protected LightSconceBlock(Properties properties) {
		super(properties);
	}
}
