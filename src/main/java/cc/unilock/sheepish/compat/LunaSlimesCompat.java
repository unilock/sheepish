package cc.unilock.sheepish.compat;

import net.lunade.slime.config.getter.ConfigValueGetter;
import net.lunade.slime.impl.SlimeInterface;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Slime;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.MobSplitEvent;

public class LunaSlimesCompat {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(MobSplitEvent.class, event -> {
			if (event.getParent() instanceof Slime par1) {
				for (Mob par2 : event.getChildren()) {
					if (par2 instanceof Slime) {
						((SlimeInterface) par2).lunaSlimes$setMergeCooldown(Math.max(ConfigValueGetter.onSplitCooldown(), ConfigValueGetter.splitCooldown()) * 2);
					}
					par2.setSilent(par1.isSilent());
				}
			}
		});
	}
}
