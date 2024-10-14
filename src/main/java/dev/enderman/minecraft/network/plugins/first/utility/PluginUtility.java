package dev.enderman.minecraft.network.plugins.first.utility;

import org.jetbrains.annotations.NotNull;

public final class PluginUtility {

    private static final String LOG_PREFIX = "[first-bungeecord-plugin]";

    public static void log(@NotNull final String message) {
        System.out.println(LOG_PREFIX + " " + message);
    }
}
