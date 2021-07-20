package me.gonzyui.zoneclaimer.utils;

import me.gonzyui.zoneclaimer.files.Configuration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ClaimUtils {
    /**
     * Send a color coded message to a Player
     * @param player  Player to send
     * @param message Message to send
     */
    public static void send(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Configuration.getPrefix() + "&r " + message));
    }
}
