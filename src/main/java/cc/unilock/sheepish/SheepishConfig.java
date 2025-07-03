package cc.unilock.sheepish;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import net.neoforged.fml.loading.FMLPaths;

public class SheepishConfig extends ReflectiveConfig {
	public static final SheepishConfig CONFIG = SheepishConfig.createToml(FMLPaths.CONFIGDIR.get(), "", "sheepish", SheepishConfig.class);

	@Comment("Prevent AbstractHorse offspring from having lesser stats than either of its parents")
	public final TrackedValue<Boolean> horseStonks = value(false);

	@Comment("Enchantments to ignore when randomly enchanting loot")
	public final TrackedValue<ValueList<String>> ignoredEnchantments = list(
			"",
			"nova_structures:shulker_boss",
			"nova_structures:shulker_miniboss"
	);
}
