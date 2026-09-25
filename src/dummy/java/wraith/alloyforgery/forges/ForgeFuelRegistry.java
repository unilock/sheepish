package wraith.alloyforgery.forges;

import net.minecraft.world.item.Item;

public class ForgeFuelRegistry {
	public static boolean hasFuel(Item item) {
		throw new AssertionError();
	}

	public static void register(Item item, ForgeFuelDefinition fuel) {
		throw new AssertionError();
	}

	public record ForgeFuelDefinition(int fuel, Item returnType) {
	}
}
