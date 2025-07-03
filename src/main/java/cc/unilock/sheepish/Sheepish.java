package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.AlmostUnifiedCompat;
import cc.unilock.sheepish.compat.AnsharCompat;
import cc.unilock.sheepish.compat.ExcessiveBuildingCompat;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.LoadingModList;
import org.slf4j.Logger;

@Mod(Sheepish.MOD_ID)
public class Sheepish {
    public static final String MOD_ID = "sheepish";
    public static final Logger LOGGER = LogUtils.getLogger();

    protected static final boolean AKASHICTOME = LoadingModList.get().getModFileById("akashictome") != null;
    protected static final boolean ALMOSTUNIFIED = LoadingModList.get().getModFileById("almostunified") != null;
    protected static final boolean ANSHAR = LoadingModList.get().getModFileById("anshar") != null;
    protected static final boolean CERULEAN = LoadingModList.get().getModFileById("cerulean") != null;
    protected static final boolean EXCESSIVE_BUILDING = LoadingModList.get().getModFileById("excessive_building") != null;

    public Sheepish(ModContainer container) {
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
}
