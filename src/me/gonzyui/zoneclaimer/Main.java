package me.gonzyui.zoneclaimer;

import me.gonzyui.zoneclaimer.commands.CommandManager;
import me.gonzyui.zoneclaimer.events.Claims;
import me.gonzyui.zoneclaimer.files.Configuration;
import me.gonzyui.zoneclaimer.files.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("Enabling plugin..");
        getCommand("zc").setExecutor(new CommandManager());


        Bukkit.getPluginManager().registerEvents(new Claims(), this);
        Configuration.setup(this);
        Data.setup(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling plugin..");
    }

    public static Main getINSTANCE() {
        return INSTANCE;
    }
}
