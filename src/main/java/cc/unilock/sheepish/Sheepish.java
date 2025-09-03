package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.AlmostUnifiedCompat;
import cc.unilock.sheepish.compat.AnsharCompat;
import cc.unilock.sheepish.compat.ExcessiveBuildingCompat;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mod(Sheepish.MOD_ID)
public class Sheepish {
    public static final String MOD_ID = "sheepish";
    public static final Logger LOGGER = LogUtils.getLogger();

    protected static final boolean AKASHICTOME = LoadingModList.get().getModFileById("akashictome") != null;
    protected static final boolean ALMOSTUNIFIED = LoadingModList.get().getModFileById("almostunified") != null;
    protected static final boolean ANSHAR = LoadingModList.get().getModFileById("anshar") != null;
    protected static final boolean CERULEAN = LoadingModList.get().getModFileById("cerulean") != null;
    protected static final boolean EMOJIFUL = LoadingModList.get().getModFileById("emojiful") != null;
    protected static final boolean EXCESSIVE_BUILDING = LoadingModList.get().getModFileById("excessive_building") != null;

    public Sheepish() {
        NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, BlockEvent.FarmlandTrampleEvent.class, event -> {
            if (CONFIG.noTrample.value()) event.setCanceled(true);
        });

//		NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, BlockDropsEvent.class, event -> {
//			if (CONFIG.droppedItemsDoNotStack.value()) separate(event.getDrops());
//		});
		NeoForge.EVENT_BUS.addListener(EventPriority.LOWEST, LivingDropsEvent.class, event -> {
			if (CONFIG.droppedItemsDoNotStack.value()) separate(event.getDrops());
		});

        if (ALMOSTUNIFIED) {
            AlmostUnifiedCompat.init();
        }
        if (ANSHAR) {
            AnsharCompat.init();
		}
        if (EXCESSIVE_BUILDING) {
            ExcessiveBuildingCompat.init();
        }
    }
	
	private static void separate(Collection<ItemEntity> drops) {
		List<ItemEntity> singleItemEntities = new ArrayList<>();
		Iterator<ItemEntity> it = drops.iterator();
		while (it.hasNext()) {
			ItemEntity entity = it.next();
			ItemStack stack = entity.getItem();
			int count = stack.getCount();
			if (count > 1) {
				it.remove();
				ItemStack singleItemStack = stack.copyWithCount(1);
				for (int i = 0; i < count; i++) {
					ItemEntity singleItemEntity = entity.copy();
					singleItemEntity.setItem(singleItemStack);
					singleItemEntities.add(singleItemEntity);
				}
			}
		}
		drops.addAll(singleItemEntities);
	}
    
    public static RegistryAccess getRegistryAccess() {
        if (ServerLifecycleHooks.getCurrentServer() == null) {
            if (FMLLoader.getDist().isClient() && Minecraft.getInstance().level != null) {
                return Minecraft.getInstance().level.registryAccess();
            }
        } else {
            return ServerLifecycleHooks.getCurrentServer().registryAccess();
        }
        throw new IllegalStateException();
    }
}
