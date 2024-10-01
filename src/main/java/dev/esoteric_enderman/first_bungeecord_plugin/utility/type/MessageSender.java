package dev.esoteric_enderman.first_bungeecord_plugin.utility.type;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class MessageSender {
    private UUID uuid;
    private boolean isConsole;

    public MessageSender(@NotNull final CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            uuid = ((ProxiedPlayer) sender).getUniqueId();
        } else {
            isConsole = true;
        }
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isConsole() {
        return isConsole;
    }

    public String toString() {
        return isConsole ? "CONSOLE" : uuid.toString();
    }
}
