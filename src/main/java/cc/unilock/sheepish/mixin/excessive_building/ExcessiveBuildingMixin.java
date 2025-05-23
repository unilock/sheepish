package cc.unilock.sheepish.mixin.excessive_building;

import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import net.minecraftforge.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.yirmiri.excessive_building.ExcessiveBuilding")
@Pseudo
public class ExcessiveBuildingMixin {
	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lfuzs/forgeconfigapiport/fabric/api/forge/v4/ForgeConfigRegistry;register(Ljava/lang/String;Lnet/neoforged/fml/config/ModConfig$Type;Lnet/minecraftforge/fml/config/IConfigSpec;Ljava/lang/String;)V"))
	private void register(ForgeConfigRegistry instance, String modId, ModConfig.Type type, IConfigSpec<?> spec, String fileName) {
		// NO-OP
	}
}
