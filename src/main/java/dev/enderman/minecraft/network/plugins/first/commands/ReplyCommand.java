package dev.enderman.minecraft.network.plugins.first.commands;

import dev.enderman.minecraft.network.plugins.first.FirstBungeeCordPlugin;
import dev.enderman.minecraft.network.plugins.first.utility.CommandUtility;
import dev.enderman.minecraft.network.plugins.first.utility.types.MessageSender;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

public final class ReplyCommand extends Command {

    private final FirstBungeeCordPlugin plugin;

    public ReplyCommand(@NotNull final FirstBungeeCordPlugin plugin) {
        super("reply");

        this.plugin = plugin;
    }

    @Override
    public void execute(@NotNull final CommandSender sender, final String @NotNull [] args) {
        final boolean isConsoleSender = sender.equals(ProxyServer.getInstance().getConsole());

        if (!(sender instanceof ProxiedPlayer) && !isConsoleSender) {
            return;
        }

        if (args.length == 0 || Arrays.toString(args).trim().isEmpty()) {
            CommandUtility.sendMessage(sender, ChatColor.RED + "Please provide a message to reply with!");
            return;
        }

        final String target = plugin.getRecentMessages().get(new MessageSender(sender).toString());

        if (target == null) {
            CommandUtility.sendMessage(sender, ChatColor.RED + "No one has messaged you yet!");
            return;
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (final String arg : args) {
            stringBuilder.append(arg).append(" ");
        }

        ProxiedPlayer targetPlayer = null;

        if (!target.equals("CONSOLE")) {
            targetPlayer = ProxyServer.getInstance().getPlayer(UUID.fromString(target));
        }

        CommandUtility.sendMessage(sender, ChatColor.AQUA + "You " + ChatColor.RESET + "-> " + ChatColor.AQUA + (target.equals("CONSOLE") ? (ChatColor.ITALIC + "Console") : (targetPlayer.getName())) + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + stringBuilder);
        CommandUtility.sendMessage(target.equals("CONSOLE") ? ProxyServer.getInstance().getConsole() : targetPlayer, ChatColor.AQUA + (isConsoleSender ? (ChatColor.ITALIC + "Console") : (sender.getName())) + ChatColor.RESET + " -> " + ChatColor.AQUA + "You" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + stringBuilder);

        plugin.getRecentMessages().put(new MessageSender(target.equals("CONSOLE") ? ProxyServer.getInstance().getConsole() : targetPlayer).toString(), new MessageSender(sender).toString());
    }
}
