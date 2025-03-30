package cc.unilock.eternaldeer.mixin.fabric;

import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.IdMapper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemColors.class)
public class ItemColorsMixin implements ColorProviderRegistryImpl.ColorMapperHolder<ItemLike, ItemColor> {
	@Shadow @Final private IdMapper<ItemColor> itemColors;

	@Override
	public ItemColor get(ItemLike item) {
		return itemColors.byId(BuiltInRegistries.ITEM.getId(item.asItem()));
	}
}
