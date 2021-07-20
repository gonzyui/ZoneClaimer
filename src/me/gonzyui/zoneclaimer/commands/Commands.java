package me.gonzyui.zoneclaimer.commands;

import org.bukkit.entity.Player;

public abstract class Commands {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String[] args);

}
