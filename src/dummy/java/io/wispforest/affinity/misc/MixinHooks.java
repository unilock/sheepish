package io.wispforest.affinity.misc;

import net.minecraft.world.item.ItemStack;

public class MixinHooks {
	public static final ThreadLocal<ItemStack> POTION_CONTENTS_COMPONENT_STACK = new ThreadLocal<>();
}
