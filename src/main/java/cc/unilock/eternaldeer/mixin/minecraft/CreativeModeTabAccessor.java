package cc.unilock.eternaldeer.mixin.minecraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeModeTab.class)
public interface CreativeModeTabAccessor {
	@Accessor
	@Final
	static ResourceLocation getDEFAULT_BACKGROUND() {
		throw new AssertionError();
	}
}
