package cc.unilock.sheepish.mixin.minecraft;

import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootTable.class)
public interface LootTableAccessor {
	@Accessor
	void setIsFrozen(boolean value);
}
