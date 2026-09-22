package cc.unilock.sheepish.module;

import cc.unilock.sheepish.Sheepish;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

public class SheepishPVP {
	public static final AttachmentType<Boolean> DATA = AttachmentType.builder(() -> Boolean.FALSE).serialize(Codec.BOOL).copyOnDeath().build();

	public static void register(IEventBus modEventBus) {
		NeoForge.EVENT_BUS.addListener(EventPriority.NORMAL, RegisterCommandsEvent.class, event -> {
			registerCommand(event.getDispatcher());
		});
		modEventBus.addListener(EventPriority.NORMAL, RegisterEvent.class, event -> {
			event.register(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, helper -> {
				helper.register(ResourceLocation.fromNamespaceAndPath(Sheepish.MOD_ID, "pvp"), DATA);
			});
		});
	}

	public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
				Commands.literal("pvp").executes(SheepishPVP::status)
						.then(Commands.literal("enable").executes(SheepishPVP::enable))
						.then(Commands.literal("disable").executes(SheepishPVP::disable))
		);
	}

	private static int status(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ctx.getSource().sendSystemMessage(Component.literal("PVP is " + (ctx.getSource().getPlayerOrException().getData(DATA) ? "enabled" : "disabled")));
		return Command.SINGLE_SUCCESS;
	}

	private static int enable(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ctx.getSource().getPlayerOrException().setData(DATA, true);
		ctx.getSource().sendSystemMessage(Component.literal("PVP enabled"));
		return Command.SINGLE_SUCCESS;
	}

	private static int disable(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ctx.getSource().getPlayerOrException().setData(DATA, false);
		ctx.getSource().sendSystemMessage(Component.literal("PVP disabled"));
		return Command.SINGLE_SUCCESS;
	}
}
