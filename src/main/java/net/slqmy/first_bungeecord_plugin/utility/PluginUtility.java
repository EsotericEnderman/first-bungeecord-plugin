package net.slqmy.first_bungeecord_plugin.utility;

import org.jetbrains.annotations.NotNull;

public final class PluginUtility {

	private static final String LOG_PREFIX = "[First-BungeeCord-Plugin]";

	public static void log(@NotNull final String message) {
		System.out.println(LOG_PREFIX + " " + message);
	}
}
