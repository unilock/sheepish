package dev.enjarai.trickster.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LightBlockEntity extends BlockEntity {
	public LightBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		super(type, pos, blockState);
	}

	public int[] getColors() {
		throw new AssertionError();
	}

	public void setColors(int[] colors) {
		throw new AssertionError();
	}
}
