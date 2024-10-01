package net.slqmy.first_bungeecord_plugin.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.slqmy.first_bungeecord_plugin.FirstBungeeCord;
import net.slqmy.first_bungeecord_plugin.utility.CommandUtility;
import net.slqmy.first_bungeecord_plugin.utility.StringUtility;
import net.slqmy.first_bungeecord_plugin.utility.type.MessageSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class MessageCommand extends Command implements TabExecutor {
	private final FirstBungeeCord plugin;

	public MessageCommand(@NotNull final FirstBungeeCord plugin) {
		super("message");

		this.plugin = plugin;
	}

	@Override
	public void execute(@NotNull final CommandSender sender, final String @NotNull [] args) {
		final boolean isConsoleSender = sender.equals(ProxyServer.getInstance().getConsole());

		if (!(sender instanceof ProxiedPlayer) && !isConsoleSender) {
			return;
		}

		if (args.length < 2 || "".equals(Arrays.toString(args).trim())) {
			CommandUtility.sendMessage(sender, ChatColor.RED + "Please provide a player to message, and a message!");
			return;
		}

		final ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

		if (target == null) {
			CommandUtility.sendMessage(sender, ChatColor.RED + "Couldn't find player!");
			return;
		}

		if (!isConsoleSender && target.getUniqueId().equals(((ProxiedPlayer) sender).getUniqueId())) {
			sender.sendMessage(new TextComponent(ChatColor.RED + "You can't message yourself!"));
			return;
		}

		final StringBuilder stringBuilder = new StringBuilder();

		for (int i = 1; i < args.length; i++) {
			stringBuilder.append(args[i]).append(" ");
		}

		CommandUtility.sendMessage(sender, ChatColor.AQUA + "You " + ChatColor.RESET + "-> " + ChatColor.AQUA + target.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + stringBuilder);
		target.sendMessage(new TextComponent(ChatColor.AQUA + (isConsoleSender ? ChatColor.ITALIC + "Console" : sender.getName()) + ChatColor.RESET + " -> " + ChatColor.AQUA + "You" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + stringBuilder));

		plugin.getRecentMessages().put(
						new MessageSender(target).toString(),
						new MessageSender(sender).toString()
		);
	}

	@Override
	public Iterable<String> onTabComplete(@NotNull final CommandSender sender, final String @NotNull [] args) {
		if (args.length == 1) {
			return StringUtility.getPartialMatches(ProxyServer.getInstance().getPlayers().stream().filter((final ProxiedPlayer player) -> !player.equals(sender)).map(CommandSender::getName).collect(Collectors.toList()), args[0]);
		}

		return new ArrayList<>();
	}
}
