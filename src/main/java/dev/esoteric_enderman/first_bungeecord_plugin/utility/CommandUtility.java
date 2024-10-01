package dev.esoteric_enderman.first_bungeecord_plugin.utility;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

public final class CommandUtility {
    public static void sendMessage(@NotNull final CommandSender commandSender, @NotNull final String message) {
        if (commandSender instanceof ProxiedPlayer) {
            commandSender.sendMessage(new TextComponent(message));
        } else if (commandSender.equals(ProxyServer.getInstance().getConsole())) {
            PluginUtility.log(ChatColor.stripColor(message));
        }
    }
}
