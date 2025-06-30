package cc.unilock.sheepish;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class SheepishConfig {
	public static final SheepishConfig CONFIG;
	public static final ModConfigSpec CONFIG_SPEC;

	static {
		Pair<SheepishConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(SheepishConfig::new);
		CONFIG = pair.getLeft();
		CONFIG_SPEC = pair.getRight();
	}

	public final ModConfigSpec.ConfigValue<List<? extends String>> ignoredEnchantments;
	public final ModConfigSpec.BooleanValue horseStonks;

	private SheepishConfig(ModConfigSpec.Builder builder) {
		this.ignoredEnchantments = builder.comment("Enchantments to ignore when randomly enchanting loot")
				.defineList("ignored_enchantments", List.of(
						"nova_structures:shulker_boss",
						"nova_structures:shulker_miniboss"
				), () -> "namespace:path", obj -> (obj instanceof String str && str.indexOf(':') > -1));
		this.horseStonks = builder.comment("Prevent AbstractHorse offspring from having lesser stats than either of its parents")
				.define("horse_stonks", false);
	}
}
