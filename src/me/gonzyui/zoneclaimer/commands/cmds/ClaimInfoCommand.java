package me.gonzyui.zoneclaimer.commands.cmds;


import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.commands.Commands;
import me.gonzyui.zoneclaimer.files.Data;
import me.gonzyui.zoneclaimer.utils.ChunkInfo;
import me.gonzyui.zoneclaimer.utils.ClaimUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ClaimInfoCommand extends Commands {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return Main.getINSTANCE().getConfig().getString("commands.claim-info");
    }

    @Override
    public String getSyntax() {
        return "/zc info";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            Chunk chunk = player.getLocation().getChunk();

            ChunkInfo cInfo = Data.getChunkInfo(chunk);
            if (cInfo == null) {
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-info.no-claim").replace("&", "§"));
            } else {
                ClaimUtils.send(player, "&8&l========================");
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-info.claim-info-zone").replace("&", "§").replace("%id%", cInfo.getId()));
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-info.claim-info-owner").replace("&", "§").replace("%owner%", Bukkit.getOfflinePlayer(cInfo.getOwner()).getName()));
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-info.claim-info-world").replace("&", "§").replace("%world", cInfo.getWorld().getName()));
                ClaimUtils.send(player, "&8&l========================");
            }
        } else {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("syntax-error").replace("%syntax%", getSyntax()));
        }
    }
}
