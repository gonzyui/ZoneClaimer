package me.gonzyui.zoneclaimer.commands.cmds;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.commands.Commands;
import me.gonzyui.zoneclaimer.files.Data;
import me.gonzyui.zoneclaimer.utils.ClaimUtils;
import me.gonzyui.zoneclaimer.utils.LandNotClaimedException;
import me.gonzyui.zoneclaimer.utils.NotLandOwnerException;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class UnclaimCommand extends Commands {
    @Override
    public String getName() {
        return "unclaim";
    }

    @Override
    public String getDescription() {
        return Main.getINSTANCE().getConfig().getString("commands.unclaim").replace("&", "§");
    }

    @Override
    public String getSyntax() {
        return "/zc unclaim";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            Chunk chunk = player.getLocation().getChunk();

            try {
                Data.removeClaim(chunk, player.getUniqueId());
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.unclaim-success").replace("&", "§"));
            } catch (LandNotClaimedException e) {
                ClaimUtils.send(player, "&c" + e.getMessage());
            } catch (NotLandOwnerException e) {
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.chunk-owner").replace("&", "§").replace("%owner%", Bukkit.getOfflinePlayer(e.getChunkInfo().getOwner()).getName()));
            }

        } else {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.syntax-error").replace("&", "§").replace("%syntax%", getSyntax()));
        }
    }

}
