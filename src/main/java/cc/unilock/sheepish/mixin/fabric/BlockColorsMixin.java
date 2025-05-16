package cc.unilock.sheepish.mixin.fabric;

import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.IdMapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockColors.class)
public class BlockColorsMixin implements ColorProviderRegistryImpl.ColorMapperHolder<Block, BlockColor> {
	@Shadow @Final private IdMapper<BlockColor> blockColors;

	@Override
	public BlockColor get(Block block) {
		return blockColors.byId(BuiltInRegistries.BLOCK.getId(block));
	}
}
