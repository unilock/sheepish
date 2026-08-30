package cc.unilock.sheepish.mixin.nomansland;

import com.farcr.nomansland.common.event.CommonSetupEvents;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CommonSetupEvents.class, remap = false)
public class CommonSetupEventsMixin {
	@WrapWithCondition(method = "registerCauldronFluidContent", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/fluids/RegisterCauldronFluidContentEvent;register(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/material/Fluid;ILnet/minecraft/world/level/block/state/properties/IntegerProperty;)V", ordinal = 1))
	private static boolean registerMilkCauldron(RegisterCauldronFluidContentEvent instance, Block block, Fluid fluid, int totalAmount, IntegerProperty levelProperty) {
		return LoadingModList.get().getModFileById("anvilcraft") == null;
	}

	@WrapWithCondition(method = "registerCauldronFluidContent", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/fluids/RegisterCauldronFluidContentEvent;register(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/material/Fluid;ILnet/minecraft/world/level/block/state/properties/IntegerProperty;)V", ordinal = 2))
	private static boolean registerHoneyCauldron(RegisterCauldronFluidContentEvent instance, Block block, Fluid fluid, int totalAmount, IntegerProperty levelProperty) {
		return LoadingModList.get().getModFileById("anvilcraft") == null;
	}
}
