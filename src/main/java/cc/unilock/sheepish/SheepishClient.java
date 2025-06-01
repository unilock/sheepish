package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.client.AkashicTomeClientCompat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Sheepish.MOD_ID, dist = Dist.CLIENT)
public class SheepishClient {
	public SheepishClient() {
		if (Sheepish.AKASHICTOME) {
			AkashicTomeClientCompat.init();
		}
	}
}
