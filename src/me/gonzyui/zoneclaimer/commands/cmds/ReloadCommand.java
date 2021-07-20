package me.gonzyui.zoneclaimer.commands.cmds;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.commands.Commands;
import org.bukkit.entity.Player;

public class ReloadCommand extends Commands {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return Main.getINSTANCE().getConfig().getString("commands.reload");
    }

    @Override
    public String getSyntax() {
        return "/zc reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("zoneclaimer.reload") || player.isOp()) {
            try {
                Main.getINSTANCE().reloadConfig();
                player.sendMessage(Main.getINSTANCE().getConfig().getString("plugin-prefix").replace("&", "§") + Main.getINSTANCE().getConfig().getString("messages.reload-success").replace("&", "§"));
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage(Main.getINSTANCE().getConfig().getString("plugin-prefix").replace("&", "§") + Main.getINSTANCE().getConfig().getString("messages.reload-failed").replace("&", "§"));
            }
        } else {
            player.sendMessage(Main.getINSTANCE().getConfig().getString("plugin-prefix").replace("&", "§") + Main.getINSTANCE().getConfig().getString("messages.no-permissions").replace("&", "§"));
        }
    }

}
