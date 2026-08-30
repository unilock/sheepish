package cc.unilock.sheepish.mixin.minecraft;

import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(WorldBorder.class)
public interface WorldBorderAccessor {
	@Accessor
	List<BorderChangeListener> getListeners();
	@Accessor
	@Mutable
	void setListeners(List<BorderChangeListener> value);
}
