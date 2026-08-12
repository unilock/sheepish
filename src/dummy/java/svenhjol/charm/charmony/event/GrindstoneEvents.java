package svenhjol.charm.charmony.event;

import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class GrindstoneEvents {
	public static final CanTakeEvent CAN_TAKE = new CanTakeEvent();
	public static final OnTakeEvent ON_TAKE = new OnTakeEvent();

	public GrindstoneEvents() {
	}

	@Nullable
	public static GrindstoneMenuInstance instance(@Nullable Player player) {
		throw new AssertionError();
	}

	public static GrindstoneMenuInstance create(GrindstoneMenu menu, Player player, Inventory inventory, Container input, Container output, ContainerLevelAccess access) {
		throw new AssertionError();
	}

	public static class GrindstoneMenuInstance {
		public GrindstoneMenu menu;
		public Player player;
		public Inventory inventory;
		public Container input;
		public Container output;
		public ContainerLevelAccess access;

		public GrindstoneMenuInstance(GrindstoneMenu menu, Player player, Inventory inventory, Container input, Container output, ContainerLevelAccess access) {
			this.menu = menu;
			this.player = player;
			this.inventory = inventory;
			this.input = input;
			this.output = output;
			this.access = access;
		}
	}

	public static class CalculateOutputEvent extends CharmEvent<CalculateOutputEvent.Handler> {
		private CalculateOutputEvent() {
		}

		public boolean invoke(GrindstoneMenuInstance instance) {
			throw new AssertionError();
		}

		@FunctionalInterface
		public interface Handler {
			boolean run(GrindstoneMenuInstance var1);
		}
	}

	public static class CanPlaceEvent extends CharmEvent<CanPlaceEvent.Handler> {
		private CanPlaceEvent() {
		}

		public boolean invoke(Container container, ItemStack itemStack) {
			throw new AssertionError();
		}

		@FunctionalInterface
		public interface Handler {
			boolean run(Container var1, ItemStack var2);
		}
	}

	public static class CanTakeEvent extends CharmEvent<CanTakeEvent.Handler> {
		private CanTakeEvent() {
		}

		public InteractionResult invoke(GrindstoneMenuInstance instance, Player player) {
			throw new AssertionError();
		}

		@FunctionalInterface
		public interface Handler {
			InteractionResult run(GrindstoneMenuInstance var1, Player var2);
		}
	}

	public static class OnTakeEvent extends CharmEvent<OnTakeEvent.Handler> {
		private OnTakeEvent() {
		}

		public boolean invoke(GrindstoneMenuInstance instance, Player player, ItemStack itemStack) {
			throw new AssertionError();
		}

		@FunctionalInterface
		public interface Handler {
			boolean run(GrindstoneMenuInstance var1, Player var2, ItemStack var3);
		}
	}
}
