package cc.unilock.eternaldeer.mixin.affinity.client.accessor;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerProfessionLayer.class)
public interface VillagerProfessionLayerAccessor {
	@Accessor
	static Int2ObjectMap<ResourceLocation> getLEVEL_LOCATIONS() {
		throw new AssertionError();
	}
}
