package cc.unilock.eternaldeer.mixin.spectrum;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "de/dafuqs/spectrum/registries/client/SpectrumColorProviders")
@Pseudo
public class SpectrumColorProvidersMixin {
	@Unique
	private static BlockColor oakLeavesBlock;
	@Unique
	private static BlockColor fernBlock;
	@Unique
	private static BlockColor shortGrassBlock;
	
	@Redirect(method = "registerColoredLeaves", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 0))
	private static Object oakLeavesBlock(ColorProviderRegistry<Block, BlockColor> instance, Object obj) {
		return oakLeavesBlock = (state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor();
	}

	@Redirect(method = "registerColoredLeaves", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 1))
	private static Object oakLeavesItem(ColorProviderRegistry<ItemLike, ItemColor> instance, Object obj) {
		return (ItemColor) (stack, tintIndex) -> oakLeavesBlock.getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex);
	}

	@Redirect(method = "registerAmaranth", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 0))
	private static Object fernBlock(ColorProviderRegistry<Block, BlockColor> instance, Object obj) {
		return fernBlock = (state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor();
	}

	@Redirect(method = "registerAmaranth", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 1))
	private static Object fernItem(ColorProviderRegistry<ItemLike, ItemColor> instance, Object obj) {
		return (ItemColor) (stack, tintIndex) -> fernBlock.getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex);
	}

	@Redirect(method = "registerAmaranth", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 2))
	private static Object largeFernBlock(ColorProviderRegistry<Block, BlockColor> instance, Object obj) {
		return (BlockColor) (state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos) : GrassColor.getDefaultColor();
	}

	@Redirect(method = "registerAmaranth", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 3))
	private static Object largeFernItem(ColorProviderRegistry<ItemLike, ItemColor> instance, Object obj) {
		return (ItemColor) (stack, tintIndex) -> GrassColor.get(0.5, 1.0);
	}

	@Redirect(method = "registerClovers", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 0))
	private static Object shortGrassBlock(ColorProviderRegistry<Block, BlockColor> instance, Object obj) {
		return shortGrassBlock = (state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor();
	}

	@Redirect(method = "registerClovers", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/client/rendering/v1/ColorProviderRegistry;get(Ljava/lang/Object;)Ljava/lang/Object;", ordinal = 1))
	private static Object shortGrassItem(ColorProviderRegistry<ItemLike, ItemColor> instance, Object obj) {
		return (ItemColor) (stack, tintIndex) -> shortGrassBlock.getColor(((BlockItem)stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex);
	}
}
