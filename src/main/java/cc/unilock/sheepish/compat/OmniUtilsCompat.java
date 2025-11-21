package cc.unilock.sheepish.compat;

import cc.unilock.sheepish.mixin.minecraft.LootTableAccessor;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import uwu.lopyluna.omni_util.register.AllItems;

import java.util.List;
import java.util.function.Consumer;

public class OmniUtilsCompat {
	public static void init() {
		NeoForge.EVENT_BUS.addListener(TagsUpdatedEvent.class, event -> {
			if (TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD.equals(event.getUpdateCause())) {
				HolderLookup.RegistryLookup<Block> blockLookup = event.getRegistryAccess().lookupOrThrow(Registries.BLOCK);
				HolderLookup.RegistryLookup<EntityType<?>> entityTypeLookup = event.getRegistryAccess().lookupOrThrow(Registries.ENTITY_TYPE);
				HolderLookup.RegistryLookup<LootTable> lootTableLookup = event.getRegistryAccess().lookupOrThrow(Registries.LOOT_TABLE);

				for (Holder<Block> holder : blockLookup.getOrThrow(Tags.Blocks.ORES_COAL)) {
					modifyLootTable(lootTableLookup.getOrThrow(holder.value().getLootTable()).value(), l -> l.addPool(
							LootPool.lootPool()
									.name("sheepish/onyx")
									.add(LootItem.lootTableItem(AllItems.ONYX))
									.when(hasSilkTouch(event.getRegistryAccess()).invert())
									.when(LootItemRandomChanceCondition.randomChance(0.10F))
									.build()
					));
				}
				for (Holder<Block> holder : blockLookup.getOrThrow(Tags.Blocks.ORES_REDSTONE)) {
					modifyLootTable(lootTableLookup.getOrThrow(holder.value().getLootTable()).value(), l -> l.addPool(
							LootPool.lootPool()
									.name("sheepish/garnet")
									.add(LootItem.lootTableItem(AllItems.GARNET))
									.when(hasSilkTouch(event.getRegistryAccess()).invert())
									.when(LootItemRandomChanceCondition.randomChance(0.25F))
									.build()
					));
				}
				for (Holder<EntityType<?>> holder : entityTypeLookup.getOrThrow(Tags.EntityTypes.BOSSES)) {
					modifyLootTable(lootTableLookup.getOrThrow(holder.value().getDefaultLootTable()).value(), l -> l.addPool(
							LootPool.lootPool()
									.name("sheepish/hex_sigil")
									.add(LootItem.lootTableItem(AllItems.HEX_SIGIL))
									.when(LootItemKilledByPlayerCondition.killedByPlayer())
									.build()
					));
				}
			}
		});

		NeoForge.EVENT_BUS.addListener(LootTableLoadEvent.class, event -> {
			if (EntityType.WITHER_SKELETON.getDefaultLootTable().location().equals(event.getName())) {
				event.getTable().addPool(
						LootPool.lootPool()
								.name("sheepish/withered_tear")
								.add(LootItem.lootTableItem(AllItems.WITHERED_TEAR))
								.when(LootItemKilledByPlayerCondition.killedByPlayer())
								.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(event.getRegistries(), 0.05F, 0.05F))
								.build()
				);
			}
		});
	}

	private static void modifyLootTable(LootTable lootTable, Consumer<LootTable> consumer) {
		boolean wasFrozen = lootTable.isFrozen();
		if (wasFrozen) {
			((LootTableAccessor) lootTable).setIsFrozen(false);
		}
		consumer.accept(lootTable);
		if (wasFrozen) {
			lootTable.freeze();
		}
	}

	private static LootItemCondition.Builder hasSilkTouch(HolderLookup.Provider registries) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
		return MatchTool.toolMatches(
				ItemPredicate.Builder.item()
						.withSubPredicate(
								ItemSubPredicates.ENCHANTMENTS,
								ItemEnchantmentsPredicate.enchantments(
										List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))
								)
						)
		);
	}
}
