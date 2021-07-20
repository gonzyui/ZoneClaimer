package me.gonzyui.zoneclaimer.files;

import me.gonzyui.zoneclaimer.Main;

public class Configuration {

    private static Main plugin;

    public static void setup(Main plugin) {
        Configuration.plugin = plugin;
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        plugin.getLogger().info("config.yml is now loaded!");
    }

    public static String getPrefix() {
        return plugin.getConfig().getString("plugin-prefix");
    }
}
