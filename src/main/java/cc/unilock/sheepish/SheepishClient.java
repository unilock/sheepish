package cc.unilock.sheepish;

import cc.unilock.sheepish.compat.client.AkashicTomeClientCompat;
import cc.unilock.sheepish.compat.client.CeruleanClientCompat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Sheepish.MOD_ID, dist = Dist.CLIENT)
public class SheepishClient {
	public SheepishClient(ModContainer container) {
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

		if (Sheepish.AKASHICTOME) {
			AkashicTomeClientCompat.init();
		}
		if (Sheepish.CERULEAN) {
			CeruleanClientCompat.init();
		}
	}
}
