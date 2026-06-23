package cc.unilock.sheepish.mixin.grapplemod;

import com.yyon.grapplinghook.neoforge.platform.NeoForgeResourcePackPlatform;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.repository.PackSource;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NeoForgeResourcePackPlatform.class, remap = false)
public class NeoForgeResourcePackPlatformMixin {
	@Unique
	private static final PackSource BUILT_IN_DEFAULT_DISABLED =PackSource.create(component ->  Component.translatable("pack.nameAndSource", component, Component.translatable("pack.source.builtin")).withStyle(ChatFormatting.GRAY), false);

	@Redirect(method = "onAddPackFinders", at = @At(value = "FIELD", target = "Lnet/minecraft/server/packs/repository/PackSource;BUILT_IN:Lnet/minecraft/server/packs/repository/PackSource;", opcode = Opcodes.GETSTATIC, remap = true))
	private static PackSource onAddPackFinders$getPackSource() {
		return BUILT_IN_DEFAULT_DISABLED;
	}
}
