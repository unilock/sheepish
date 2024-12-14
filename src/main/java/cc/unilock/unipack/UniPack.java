package cc.unilock.unipack;

import com.almostreliable.unified.api.AlmostUnified;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import org.slf4j.Logger;

@Mod(UniPack.MOD_ID)
public class UniPack {
    public static final String MOD_ID = "unipack";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final boolean ALMOSTUNIFIED = ModList.get().isLoaded("almostunified");

    public UniPack() {
        if (ALMOSTUNIFIED) {
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::blockDrops);
        }
    }

    private void blockDrops(BlockDropsEvent event) {
        event.getDrops().forEach(i -> {
            final ItemStack stack = i.getItem();
            final Item item = stack.getItem();
            final Item replacement = AlmostUnified.INSTANCE.getVariantItemTarget(item);
            if (replacement != null && !(item == replacement)) {
                i.setItem(new ItemStack(replacement, stack.getCount()));
            }
        });
    }
}
