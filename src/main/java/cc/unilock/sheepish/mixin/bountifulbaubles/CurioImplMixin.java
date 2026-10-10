package cc.unilock.sheepish.mixin.bountifulbaubles;

import com.jinqinxixi.bountifulbaubles.modifier.CurioImpl;
import org.spongepowered.asm.mixin.Mixin;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@Mixin(value = CurioImpl.class, remap = false)
public abstract class CurioImplMixin implements ICurio {
	@Override
	public void curioTick(SlotContext slotContext) {
		if (this.getStack().getItem() instanceof ICurioItem curioItem) {
			curioItem.curioTick(slotContext, this.getStack());
		}
	}
}
