package dev.esoteric_enderman.first_bungeecord_plugin.commands;

import dev.esoteric_enderman.first_bungeecord_plugin.utility.StringUtility;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class FruitCommand extends Command implements TabExecutor {
    public FruitCommand() {
        super("fruit");
    }

    @Override
    public void execute(@NotNull final CommandSender sender, final String @NotNull [] args) {

    }

    @Override
    public Iterable<String> onTabComplete(@NotNull final CommandSender sender, final String @NotNull [] args) {
        return switch (args.length) {
            case 1 -> StringUtility.getPartialMatches(Arrays.asList("Apple", "Grape", "Pear", "Kiwi"), args[0]);
            case 2 ->
                    StringUtility.getPartialMatches(ProxyServer.getInstance().getPlayers().stream().map(CommandSender::getName).collect(Collectors.toList()), args[1]);
            default -> new ArrayList<>();
        };

    }
}
