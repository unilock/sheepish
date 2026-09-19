package cc.unilock.sheepish.module;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.UserWhiteList;
import net.minecraft.server.players.UserWhiteListEntry;
import net.neoforged.fml.loading.FMLPaths;

public class PVPCommand {
	public static final UserWhiteList pvpWhitelist = new UserWhiteList(FMLPaths.CONFIGDIR.get().resolve("sheepish-pvp.json").toFile());

	public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
				Commands.literal("pvp").executes(PVPCommand::status)
						.then(Commands.literal("enable").executes(PVPCommand::enable))
						.then(Commands.literal("disable").executes(PVPCommand::disable))
		);
	}

	private static int status(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		ctx.getSource().sendSystemMessage(Component.literal("PVP is " + (pvpWhitelist.isWhiteListed(ctx.getSource().getPlayerOrException().getGameProfile()) ? "enabled" : "disabled")));
		return Command.SINGLE_SUCCESS;
	}

	private static int enable(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		pvpWhitelist.add(new UserWhiteListEntry(ctx.getSource().getPlayerOrException().getGameProfile()));
		ctx.getSource().sendSystemMessage(Component.literal("PVP enabled"));
		return Command.SINGLE_SUCCESS;
	}

	private static int disable(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		pvpWhitelist.remove(ctx.getSource().getPlayerOrException().getGameProfile());
		ctx.getSource().sendSystemMessage(Component.literal("PVP disabled"));
		return Command.SINGLE_SUCCESS;
	}
}
