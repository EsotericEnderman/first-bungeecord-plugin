package dev.enderman.minecraft.network.plugins.first.utility;

import net.md_5.bungee.api.ChatColor;

public final class ChatUtility {

    private static final String LINE_CHAT_PREFIX = ChatColor.DARK_GRAY + "| ";

    public static String getLineChatPrefix() {
        return LINE_CHAT_PREFIX;
    }
}
