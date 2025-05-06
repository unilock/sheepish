package moriyashiine.anthropophagy.common.item;

import moriyashiine.anthropophagy.common.init.ModComponentTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FleshItem extends Item {
	public FleshItem(Properties properties) {
		super(properties);
	}

	public static String getOwnerName(ItemStack stack) {
		return stack.getOrDefault(ModComponentTypes.OWNER_NAME, "");
	}

	public static boolean isOwnerPlayer(ItemStack stack) {
		return stack.getOrDefault(ModComponentTypes.FROM_PLAYER, false);
	}

	public static void setOwner(ItemStack stack, String ownerName, boolean fromPlayer) {
		if (ownerName.isEmpty()) {
			return;
		}
		stack.set(ModComponentTypes.OWNER_NAME, ownerName);
		stack.set(ModComponentTypes.FROM_PLAYER, fromPlayer);
	}
}
