package cc.unilock.sheepish.mixin.frozenlib.accessor;

import net.minecraft.core.Holder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Holder.Reference.class)
public interface HolderReferenceAccessor<T> {
	@Invoker
	void callBindValue(T value);
}
