package dev.enderman.minecraft.network.plugins.first.commands;

import dev.enderman.minecraft.network.plugins.first.utility.ChatUtility;
import dev.enderman.minecraft.network.plugins.first.utility.PluginUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.jetbrains.annotations.NotNull;

public final class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(@NotNull final CommandSender sender, final String @NotNull [] args) {
        if (sender instanceof ProxiedPlayer player) {
            player.sendMessage(new TextComponent(ChatUtility.getLineChatPrefix() + ChatColor.GRAY + "Pong!"));
        } else {
            PluginUtility.log("Pong!");
        }
    }
}
