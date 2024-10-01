package dev.esoteric_enderman.first_bungeecord_plugin.utility;

import org.jetbrains.annotations.NotNull;

public final class PluginUtility {

    private static final String LOG_PREFIX = "[first-bungeecord-plugin]";

    public static void log(@NotNull final String message) {
        System.out.println(LOG_PREFIX + " " + message);
    }
}
