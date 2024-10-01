package dev.esoteric_enderman.first_bungeecord_plugin.utility;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class StringUtility {

    public static List<String> getPartialMatches(@NotNull final List<String> strings, @NotNull final String matcher) {
        return strings.stream().filter((final String string) -> string.toLowerCase().startsWith(matcher.toLowerCase())).collect(Collectors.toList());
    }
}
