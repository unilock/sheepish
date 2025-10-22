package cc.unilock.sheepish.mixin.patchouli;

import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.HolderLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.patchouli.common.book.Book;

@Mixin(value = Book.class, remap = false)
public class BookMixin {
	@WrapOperation(method = "lambda$new$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/data/registries/VanillaRegistries;createLookup()Lnet/minecraft/core/HolderLookup$Provider;"))
	private static HolderLookup.Provider lambda$createLookup(Operation<HolderLookup.Provider> original) {
		try {
			return Sheepish.getRegistryAccess();
		} catch (IllegalStateException ignored) {
			return original.call();
		}
	}
}
