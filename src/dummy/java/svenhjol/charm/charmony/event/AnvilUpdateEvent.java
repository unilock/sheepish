package svenhjol.charm.charmony.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class AnvilUpdateEvent {
	public static final AnvilUpdateEvent INSTANCE = new AnvilUpdateEvent();

	private AnvilUpdateEvent() {
	}

	public Optional<AnvilRecipe> invoke(Player player, ItemStack input, ItemStack material, long cost) {
		throw new AssertionError();
	}

	public static class AnvilRecipe {
		public ItemStack output;
		public int experienceCost;
		public int materialCost;

		public AnvilRecipe() {
		}
	}
}
