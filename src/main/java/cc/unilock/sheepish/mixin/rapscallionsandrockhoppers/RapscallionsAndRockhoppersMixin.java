package cc.unilock.sheepish.mixin.rapscallionsandrockhoppers;

import cc.unilock.sheepish.Sheepish;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import house.greenhouse.rapscallionsandrockhoppers.RapscallionsAndRockhoppers;
import house.greenhouse.rapscallionsandrockhoppers.entity.PenguinVariant;
import house.greenhouse.rapscallionsandrockhoppers.util.RockhoppersResourceKeys;
import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RapscallionsAndRockhoppers.class, remap = false)
public class RapscallionsAndRockhoppersMixin {
	@WrapMethod(method = "getBiomePopulationPenguinTypeRegistry")
	private static Registry<PenguinVariant> getBiomePopulationPenguinTypeRegistry(Operation<Registry<PenguinVariant>> original) {
		var ret = original.call();
		if (ret != null) {
			return ret;
		} else {
			return Sheepish.getRegistryAccess().registryOrThrow(RockhoppersResourceKeys.PENGUIN_VARIANT);
		}
	}
}
