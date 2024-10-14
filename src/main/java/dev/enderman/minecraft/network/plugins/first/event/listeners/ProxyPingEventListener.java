package dev.enderman.minecraft.network.plugins.first.event.listeners;

import dev.enderman.minecraft.network.plugins.first.utility.ChatUtility;
import dev.enderman.minecraft.network.plugins.first.utility.DebugUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public final class ProxyPingEventListener implements Listener {

    private static final Random random = new Random();

    private Favicon favicon;

    public ProxyPingEventListener() {
        try {
            favicon = Favicon.create(ImageIO.read(new File("assets/server-icon.png")));
        } catch (final IOException exception) {
            DebugUtility.logError(exception, "There was an unexpected error while loading the server favicon!");
        }
    }

    @EventHandler
    public void onProxyPing(@NotNull final ProxyPingEvent event) {
        final ServerPing response = event.getResponse();

        response.setDescriptionComponent(
                new TextComponent(
                        ChatUtility.getLineChatPrefix() + ChatColor.GREEN + "Localhost BungeeCord Testing Server\n" + ChatUtility.getLineChatPrefix() + ChatColor.GRAY + "Version: " + ChatColor.YELLOW + ChatColor.UNDERLINE + "1.20.1"
                )
        );

        response.setPlayers(
                new ServerPing.Players(
                        random.nextInt(250_000, 350_001),
                        random.nextInt(100_000, 250_000),
                        new ServerPing.PlayerInfo[]{

                        }
                )
        );

        response.setVersion(
                new ServerPing.Protocol("Please use 1.20.1 or above!", 763)
        );

        if (favicon != null) {
            response.setFavicon(favicon);
        }

        event.setResponse(response);
    }
}
