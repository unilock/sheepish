package cc.unilock.eternaldeer.mixin.armistice;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import symbolics.division.armistice.Armistice;
import symbolics.division.armistice.component.*;
import symbolics.division.armistice.mecha.MechaSkin;
import symbolics.division.armistice.mecha.schematic.ArmorSchematic;
import symbolics.division.armistice.mecha.schematic.ChassisSchematic;
import symbolics.division.armistice.mecha.schematic.HullSchematic;
import symbolics.division.armistice.mecha.schematic.OrdnanceSchematic;
import symbolics.division.armistice.registry.ArmisticeCreativeModeTabRegistrar;
import symbolics.division.armistice.registry.ArmisticeDataComponentTypeRegistrar;
import symbolics.division.armistice.registry.ArmisticeItemRegistrar;
import symbolics.division.armistice.registry.ArmisticeRegistries;

import java.util.List;

@Mixin(value = ArmisticeCreativeModeTabRegistrar.class, remap = false)
public class ArmisticeCreativeModeTabRegistrarMixin {
	/**
	 * @author unilock
	 * @reason fix registry access
	 */
	@Overwrite
	private static void lambda$static$6(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
		RegistryAccess access = getRegistryAccess();

		List<ArmorSchematic> armorSchematics = access.registryOrThrow(ArmisticeRegistries.ARMOR_KEY).stream().toList();
		List<HullSchematic> hullSchematics = access.registryOrThrow(ArmisticeRegistries.HULL_KEY).stream().toList();
		List<ChassisSchematic> chassisSchematics = access.registryOrThrow(ArmisticeRegistries.CHASSIS_KEY).stream().toList();
		List<OrdnanceSchematic> ordnanceSchematics = ArmisticeRegistries.ORDNANCE.stream().toList();
		List<MechaSkin> skins = access.registryOrThrow(ArmisticeRegistries.SKIN_KEY).stream()
				.filter(skin -> !skin.id().equals(Armistice.id("default")))
				.toList();

		armorSchematics.forEach(schematic -> output.accept(
				new ItemStack(
						Holder.direct(ArmisticeItemRegistrar.ARMOR_SCHEMATIC),
						1,
						DataComponentPatch.builder()
								.set(ArmisticeDataComponentTypeRegistrar.ARMOR_SCHEMATIC, new ArmorSchematicComponent(schematic))
								.build()
				)
		));

		hullSchematics.forEach(schematic -> output.accept(
				new ItemStack(
						Holder.direct(ArmisticeItemRegistrar.HULL_SCHEMATIC),
						1,
						DataComponentPatch.builder()
								.set(ArmisticeDataComponentTypeRegistrar.HULL_SCHEMATIC, new HullSchematicComponent(schematic))
								.build()
				)
		));

		chassisSchematics.forEach(schematic -> output.accept(
				new ItemStack(
						Holder.direct(ArmisticeItemRegistrar.CHASSIS_SCHEMATIC),
						1,
						DataComponentPatch.builder()
								.set(ArmisticeDataComponentTypeRegistrar.CHASSIS_SCHEMATIC, new ChassisSchematicComponent(schematic))
								.build()
				)
		));

		ordnanceSchematics.forEach(schematic -> output.accept(
				new ItemStack(
						Holder.direct(ArmisticeItemRegistrar.ORDNANCE_SCHEMATIC),
						1,
						DataComponentPatch.builder()
								.set(ArmisticeDataComponentTypeRegistrar.ORDNANCE_SCHEMATIC, new OrdnanceSchematicComponent(schematic))
								.build()
				)
		));

		skins.forEach(skin -> output.accept(
				new ItemStack(
						Holder.direct(ArmisticeItemRegistrar.MECHA_SKIN),
						1,
						DataComponentPatch.builder()
								.set(ArmisticeDataComponentTypeRegistrar.SKIN, new SkinComponent(skin))
								.build()
				)
		));
	}

	@Unique
	private static RegistryAccess getRegistryAccess() {
		if (ServerLifecycleHooks.getCurrentServer() == null) {
			if (FMLLoader.getDist().isClient() && Minecraft.getInstance().level != null) {
				return Minecraft.getInstance().level.registryAccess();
			}
		} else {
			return ServerLifecycleHooks.getCurrentServer().registryAccess();
		}
		throw new IllegalStateException();
	}
}
