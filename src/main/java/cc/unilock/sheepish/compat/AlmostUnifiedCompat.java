package cc.unilock.sheepish.compat;

import com.almostreliable.unified.api.AlmostUnified;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

import java.util.Collection;

public class AlmostUnifiedCompat {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, AlmostUnifiedCompat::blockDrops);
		NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, AlmostUnifiedCompat::livingDrops);
	}

	public static void blockDrops(BlockDropsEvent event) {
		unify(event.getDrops());
	}

	public static void livingDrops(LivingDropsEvent event) {
		unify(event.getDrops());
	}

	private static void unify(Collection<ItemEntity> drops) {
		drops.forEach(i -> {
			final ItemStack stack = i.getItem();
			final Item item = stack.getItem();
			final Item replacement = AlmostUnified.INSTANCE.getVariantItemTarget(item);
			if (replacement != null && !item.equals(replacement)) {
				i.setItem(new ItemStack(replacement, stack.getCount()));
			}
		});
	}
}
