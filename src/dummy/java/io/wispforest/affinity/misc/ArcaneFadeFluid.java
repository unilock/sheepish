package io.wispforest.affinity.misc;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public abstract class ArcaneFadeFluid extends FlowingFluid {
	public static final Event<OnTouch> ENTITY_TOUCH_EVENT = EventFactory.createArrayBacked(OnTouch.class, listeners -> entity -> {
		for (var listener : listeners) {
			listener.onTouch(entity);
		}
	});

	public static final Event<OnTouch> ENTITY_TICK_IN_FADE_EVENT = EventFactory.createArrayBacked(OnTouch.class, listeners -> entity -> {
		for (var listener : listeners) {
			listener.onTouch(entity);
		}
	});

	@Override
	public Fluid getFlowing() {
		throw new AssertionError();
	}

	@Override
	public Fluid getSource() {
		throw new AssertionError();
	}

	@Override
	protected boolean canConvertToSource(Level level) {
		throw new AssertionError();
	}

	@Override
	protected void beforeDestroyingBlock(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState) {
		throw new AssertionError();
	}

	@Override
	protected int getSlopeFindDistance(LevelReader levelReader) {
		throw new AssertionError();
	}

	@Override
	protected int getDropOff(LevelReader levelReader) {
		throw new AssertionError();
	}

	@Override
	public Item getBucket() {
		throw new AssertionError();
	}

	@Override
	protected boolean canBeReplacedWith(FluidState fluidState, BlockGetter blockGetter, BlockPos blockPos, Fluid fluid, Direction direction) {
		throw new AssertionError();
	}

	@Override
	public int getTickDelay(LevelReader levelReader) {
		throw new AssertionError();
	}

	@Override
	protected float getExplosionResistance() {
		throw new AssertionError();
	}

	@Override
	protected BlockState createLegacyBlock(FluidState fluidState) {
		throw new AssertionError();
	}

	@Override
	public boolean isSource(FluidState fluidState) {
		throw new AssertionError();
	}

	@Override
	public int getAmount(FluidState fluidState) {
		throw new AssertionError();
	}

	public static class Still extends ArcaneFadeFluid { }

	public static class Flowing extends ArcaneFadeFluid {}

	public interface OnTouch {
		void onTouch(Entity entity);
	}
}
