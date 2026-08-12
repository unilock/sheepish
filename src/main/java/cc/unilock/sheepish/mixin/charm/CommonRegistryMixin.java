package cc.unilock.sheepish.mixin.charm;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

@Mixin(targets = "svenhjol/charm/charmony/common/CommonRegistry")
@Pseudo
public class CommonRegistryMixin {
	@Redirect(method = "lambda$pointOfInterestType$28", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/village/poi/PoiTypes;registerBlockStates(Lnet/minecraft/core/Holder;Ljava/util/Set;)V"))
	private void lambda(Holder<PoiType> poi, Set<BlockState> states) {
		// NO-OP
	}
}
