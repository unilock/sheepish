package cc.unilock.sheepish;

import com.almostreliable.unified.api.AlmostUnified;
import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.neoforge.api.forge.v4.ForgeConfigRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeConfigSpec;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import org.slf4j.Logger;
import vazkii.akashictome.MorphingHandler;

import java.lang.invoke.MethodHandles;
import java.util.Collection;

@Mod(Sheepish.MOD_ID)
public class Sheepish {
    public static final String MOD_ID = "sheepish";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final boolean AKASHICTOME = LoadingModList.get().getModFileById("akashictome") != null;
    private static final boolean ALMOSTUNIFIED = LoadingModList.get().getModFileById("almostunified") != null;
    private static final boolean ANSHAR = LoadingModList.get().getModFileById("anshar") != null;
    private static final boolean EXCESSIVE_BUILDING = LoadingModList.get().getModFileById("excessive_building") != null;

    public Sheepish() {
        if (AKASHICTOME) {
            NeoForge.EVENT_BUS.addListener(InputEvent.InteractionKeyMappingTriggered.class, event -> {
                if (event.isAttack() && Minecraft.getInstance().hitResult != null && Minecraft.getInstance().hitResult.getType() == HitResult.Type.MISS) {
                    MorphingHandler.INSTANCE.onPlayerLeftClick(new PlayerInteractEvent.LeftClickEmpty(Minecraft.getInstance().player));
                }
            });
        }
        if (ALMOSTUNIFIED) {
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::blockDrops);
            NeoForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::livingDrops);
        }
        if (ANSHAR) {
            try {
                ModConfigSpec spec = (ModConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("com.lgmrszd.anshar.config.ServerConfig"), "CONFIG_SPEC", ModConfigSpec.class).invoke(); 
                ModList.get().getModContainerById("anshar").orElseThrow().registerConfig(ModConfig.Type.SERVER, spec);
            } catch (Throwable e) {
                throw new RuntimeException("Failed to find / invoke handle for Anshar's ServerConfig.CONFIG_SPEC", e);
            }
		}
        if (EXCESSIVE_BUILDING) {
            try {
                ForgeConfigSpec spec = (ForgeConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("net.yirmiri.excessive_building.EBConfig"), "COMMON", ForgeConfigSpec.class).invoke();
                ForgeConfigRegistry.INSTANCE.register("excessive_building", ModConfig.Type.COMMON, spec, "excessive_building-config.toml");
            } catch (Throwable e) {
                throw new RuntimeException("Failed to find / invoke handle for Excessive Building's EBConfig.COMMON", e);
            }
            try {
                ForgeConfigSpec spec = (ForgeConfigSpec) MethodHandles.publicLookup().findStaticGetter(Class.forName("net.yirmiri.excessive_building.EBClientConfig"), "CLIENT", ForgeConfigSpec.class).invoke();
                ForgeConfigRegistry.INSTANCE.register("excessive_building", ModConfig.Type.CLIENT, spec, "excessive_building-config-client.toml");
            } catch (Throwable e) {
                throw new RuntimeException("Failed to find / invoke handle for Excessive Building's EBClientConfig.CLIENT", e);
            }
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
