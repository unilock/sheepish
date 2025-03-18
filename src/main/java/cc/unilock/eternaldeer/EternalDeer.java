package cc.unilock.eternaldeer;

import com.almostreliable.unified.api.AlmostUnified;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import org.slf4j.Logger;

import java.util.Collection;

@Mod(EternalDeer.MOD_ID)
public class EternalDeer {
    public static final String MOD_ID = "eternaldeer";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final boolean ALMOSTUNIFIED = ModList.get().isLoaded("almostunified");

    public EternalDeer() {
        if (ALMOSTUNIFIED) {
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::blockDrops);
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::livingDrops);
        }
    }

    private void blockDrops(BlockDropsEvent event) {
        unify(event.getDrops());
    }

    private void livingDrops(LivingDropsEvent event) {
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
