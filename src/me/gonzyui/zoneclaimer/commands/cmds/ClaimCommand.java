package me.gonzyui.zoneclaimer.commands.cmds;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.commands.Commands;
import me.gonzyui.zoneclaimer.files.Data;
import me.gonzyui.zoneclaimer.utils.ClaimUtils;
import me.gonzyui.zoneclaimer.utils.LandAlreadyClaimedException;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ClaimCommand extends Commands {

    @Override
    public String getName() {
        return "claim";
    }

    @Override
    public String getDescription() {
        return Main.getINSTANCE().getConfig().getString("commands.claim").replace("&", "§");
    }

    @Override
    public String getSyntax() {
        return "/zc claim";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            Chunk chunk = player.getLocation().getChunk();

            try {
                Data.addClaim(chunk, player.getUniqueId());
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-success"));
            } catch (LandAlreadyClaimedException e) {
                ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.claim-failed").replace("&", "§").replace("%owner%", Bukkit.getOfflinePlayer(e.getChunkInfo().getOwner()).getName()));
            }
        } else {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.syntax-error").replace("&", "§").replace("%syntax%", getSyntax()));
        }
    }
}
