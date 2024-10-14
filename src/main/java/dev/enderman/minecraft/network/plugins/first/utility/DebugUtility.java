package dev.enderman.minecraft.network.plugins.first.utility;

import org.jetbrains.annotations.NotNull;

public final class DebugUtility {

    public static void log(final Object @NotNull ... values) {
        for (final Object value : values) {
            System.out.println(value);
        }
    }

    public static void logError(@NotNull final Exception exception, @NotNull final String message) {
        System.out.println(message);
        System.out.println(exception.getMessage());
        System.out.println(exception);
        exception.printStackTrace();
    }
}
