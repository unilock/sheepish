package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.client.AkashicTomeClientCompat;
import cc.unilock.sheepish.compat.client.CeruleanClientCompat;
import cc.unilock.sheepish.compat.client.EmojifulCompat;
import cc.unilock.sheepish.module.Mc122477Fix;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Sheepish.MOD_ID, dist = Dist.CLIENT)
public class SheepishClient {
	public SheepishClient() {
		if (Sheepish.AKASHICTOME) {
			AkashicTomeClientCompat.init();
		}
		if (Sheepish.CERULEAN) {
			CeruleanClientCompat.init();
		}
		if (Sheepish.EMOJIFUL) {
			EmojifulCompat.init();
		}

		NeoForge.EVENT_BUS.register(new Mc122477Fix());
	}
}
