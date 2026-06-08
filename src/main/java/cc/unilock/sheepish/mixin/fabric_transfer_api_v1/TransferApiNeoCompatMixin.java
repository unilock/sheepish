package cc.unilock.sheepish.mixin.fabric_transfer_api_v1;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.sinytra.fabric.transfer_api.TransferApiNeoCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TransferApiNeoCompat.class, remap = false)
public class TransferApiNeoCompatMixin {
	@ModifyExpressionValue(method = "lambda$wrapProviderSafely$10", at = @At(value = "INVOKE", target = "Ljava/lang/ThreadLocal;get()Ljava/lang/Object;"))
	private static Object wrapProviderSafely(Object original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) BlockEntity blockEntity) {
		String namespace = null;
		if (blockEntity != null) {
			ResourceLocation rl = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntity.getType());
			if (rl != null) {
				namespace = rl.getNamespace();
			}
		}
		return (Boolean) original && (state.is(Blocks.COMPOSTER) || "minecraft".equals(namespace));
	}
}
