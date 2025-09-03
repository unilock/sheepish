package cc.unilock.sheepish.mixin.minecraft;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

import static cc.unilock.sheepish.SheepishConfig.CONFIG;

@Mixin(value = Block.class, priority = 999)
public class BlockMixin {
	@ModifyReturnValue(method = "getDrops*", at = @At("RETURN"))
	private static List<ItemStack> getDrops(List<ItemStack> drops) {
		if (!CONFIG.droppedItemsDoNotStack.value() || drops == null) return drops;
		List<ItemStack> ret = new ArrayList<>();
		for (ItemStack stack : drops) {
			ItemStack single = stack.copy();
			single.setCount(1);
			for (int i = 0; i < stack.getCount()-1; i++) {
				ret.add(single.copy());
			}
			ret.add(single);
		}
		return ret;
	}
}
