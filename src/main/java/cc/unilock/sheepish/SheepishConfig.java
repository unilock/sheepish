package cc.unilock.sheepish;

import folk.sisby.kaleido.api.ReflectiveConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ValueList;
import net.neoforged.fml.loading.FMLPaths;

public class SheepishConfig extends ReflectiveConfig {
	public static final SheepishConfig CONFIG = SheepishConfig.createToml(FMLPaths.CONFIGDIR.get(), "", "sheepish", SheepishConfig.class);

	@Comment("Allow players to eat food regardless of hunger bar fullness")
	public final TrackedValue<Boolean> alwaysEat = value(false);

	@Comment("Disable increasing repair cost on armor and tools")
	public final TrackedValue<Boolean> disableRepairCost = value(false);

	@Comment("Disable Sodium's core shader resource pack warning")
	public final TrackedValue<Boolean> disableSodiumCoreShaderWarning = value(false);

	@Comment("Disable custom splash texts from Blueprint, FrozenLib, and NeoForge")
	public final TrackedValue<Boolean> disableSplashes = value(false);

	@Comment("From Fabrication: \"When dropping items of the same type they don't merge into a bigger stack.\"")
	public final TrackedValue<Boolean> droppedItemsDoNotStack = value(false);

	@Comment({
			"Enable Emojiful's pixelated Twemoji set, without the \"Blobs\", \"Discord\", or \"Pepe\" categories",
			"Requires \"Emojiful.EmojiTypes.custom = false\" in \"emojiful-client.toml\""
	})
	public final TrackedValue<Boolean> emojifulPixelatedTwemoji = value(false);

	@Comment("Prevent AbstractHorse offspring from having lesser stats than either of its parents")
	public final TrackedValue<Boolean> horseStonks = value(false);

	@Comment("Enchantments to ignore when randomly enchanting loot")
	public final TrackedValue<ValueList<String>> ignoredEnchantments = list("",
			"nova_structures:boss_behavior",
			"nova_structures:shulker_boss",
			"nova_structures:shulker_miniboss",
			"pastel:big_catch",
			"pastel:clovers_favor",
			"pastel:disarming",
			"pastel:exuberance",
			"pastel:first_strike",
			"pastel:foundry",
			"pastel:improved_critical",
			"pastel:indestructible",
			"pastel:inertia",
			"pastel:inexorable",
			"pastel:inventory_insertion",
			"pastel:pest_control",
			"pastel:razing",
			"pastel:resonance",
			"pastel:serendipity_reel",
			"pastel:sniping",
			"pastel:steadfast",
			"pastel:tight_grip",
			"pastel:treasure_hunter",
			"pastel:voiding"
	);

	@Comment("Prevent all entities from trampling farmland")
	public final TrackedValue<Boolean> noTrample = value(false);
}
