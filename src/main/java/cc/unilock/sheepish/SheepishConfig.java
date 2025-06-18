package cc.unilock.sheepish;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SheepishConfig {
	public static final SheepishConfig CONFIG;
	public static final ModConfigSpec CONFIG_SPEC;

	static {
		Pair<SheepishConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(SheepishConfig::new);
		CONFIG = pair.getLeft();
		CONFIG_SPEC = pair.getRight();
	}
	
	public final ModConfigSpec.BooleanValue horseStonks;

	private SheepishConfig(ModConfigSpec.Builder builder) {
		this.horseStonks = builder.comment("Horse Stonks")
				.translation("sheepish.config.horse_stonks")
				.define("horse_stonks", false);
	}
}
