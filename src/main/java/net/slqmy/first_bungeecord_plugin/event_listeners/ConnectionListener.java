package net.slqmy.first_bungeecord_plugin.event_listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.slqmy.first_bungeecord_plugin.Main;
import net.slqmy.first_bungeecord_plugin.utility.ChatUtility;
import net.slqmy.first_bungeecord_plugin.utility.type.MessageSender;
import org.jetbrains.annotations.NotNull;

public final class ConnectionListener implements Listener {
	private final Main plugin;

	public ConnectionListener(@NotNull final Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPostLogin(@NotNull final PostLoginEvent event) {
		event.getPlayer().sendMessage(new TextComponent(ChatUtility.getLineChatPrefix() + ChatColor.GRAY + "Thank you for joining " + ChatColor.DARK_GREEN + ChatColor.BOLD + "The Slimy Swamp" + ChatColor.GRAY + "!"));
	}

	@EventHandler
	public void onPlayerDisconnect(@NotNull final PlayerDisconnectEvent event) {
		plugin.getRecentMessages().remove(new MessageSender(event.getPlayer()).toString());
	}
}
