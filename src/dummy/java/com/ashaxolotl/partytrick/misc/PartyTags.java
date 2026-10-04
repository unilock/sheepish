package com.ashaxolotl.partytrick.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PartyTags {
	public static final TagKey<Item> CAST_ON_CONSUMPTION_BLACKLIST = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("partytrick", "cast_on_consumption_blacklist"));
	public static final TagKey<Item> CAST_ON_USE_WHITELIST = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("partytrick", "cast_on_use_whitelist"));
}
