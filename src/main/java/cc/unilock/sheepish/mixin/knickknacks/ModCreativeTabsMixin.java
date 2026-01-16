package cc.unilock.sheepish.mixin.knickknacks;

import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tech.thatgravyboat.knickknacks.common.registries.ModCreativeTabs;

@Mixin(ModCreativeTabs.class)
public class ModCreativeTabsMixin {
	@Unique
	private static final Rarity[] RARITIES = new Rarity[]{Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE, Rarity.EPIC};

	@Redirect(method = "lambda$static$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Rarity;values()[Lnet/minecraft/world/item/Rarity;"))
	private static Rarity[] displayItemsLambda$values() {
		return RARITIES;
	}
}
