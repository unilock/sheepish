package cc.unilock.sheepish.mixin.lovely_sparkle_pieces;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.royling.lovelysparklepieces.LovelySparklePieces;
import net.royling.lovelysparklepieces.ModRecipe.ModRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ModRecipe.class, remap = false)
public class ModRecipeMixin {
	@Redirect(method = "lambda$static$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/RecipeType;register(Ljava/lang/String;)Lnet/minecraft/world/item/crafting/RecipeType;"))
	private static <T extends Recipe<?>> RecipeType<T> register(String identifier) {
		return RecipeType.simple(LovelySparklePieces.resLoc(identifier));
	}
}
