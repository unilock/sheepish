package cc.unilock.sheepish.compat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import wraith.alloyforgery.forges.ForgeFuelRegistry;

public class AlloyForgeryCompat {
	@SuppressWarnings("ConstantConditions")
	public static void init() {
		NeoForge.EVENT_BUS.addListener(ServerAboutToStartEvent.class, event -> {
			BuiltInRegistries.ITEM.stream().parallel().forEach(item -> {
				ItemStack stack = item.getDefaultInstance();
				int burnTime = stack.getBurnTime(RecipeType.SMELTING);
				if (burnTime > 0 && !ForgeFuelRegistry.hasFuel(item)) {
					ItemStack remainder = stack.getCraftingRemainingItem();
					ForgeFuelRegistry.register(item, new ForgeFuelRegistry.ForgeFuelDefinition(burnTime, remainder.isEmpty() ? null : remainder.getItem()));
				}
			});
		});
	}
}
