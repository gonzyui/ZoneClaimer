package me.gonzyui.zoneclaimer.commands;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.commands.cmds.ClaimCommand;
import me.gonzyui.zoneclaimer.commands.cmds.ClaimInfoCommand;
import me.gonzyui.zoneclaimer.commands.cmds.ReloadCommand;
import me.gonzyui.zoneclaimer.commands.cmds.UnclaimCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private List<Commands> commands;

    public CommandManager() {
        commands = new ArrayList<>();
        commands.add(new ClaimCommand());
        commands.add(new UnclaimCommand());
        commands.add(new ClaimInfoCommand());
        commands.add(new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                for (Commands cmd : commands) {
                    if (args[0].equalsIgnoreCase(cmd.getName())) {
                        cmd.perform(p, args);
                    }
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getINSTANCE().getConfig().getString("plugin-help-title")));
                for (Commands cmd : commands) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&c" + cmd.getSyntax() + "&7 - " + cmd.getDescription()));
                }
            }
        } else {
            sender.sendMessage(Main.getINSTANCE().getConfig().getString("plugin-prefix") + Main.getINSTANCE().getConfig().getString("messages.no-console").replace("&", "§"));
        }

        return true;
    }
}
