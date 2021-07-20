package me.gonzyui.zoneclaimer.events;

import me.gonzyui.zoneclaimer.Main;
import me.gonzyui.zoneclaimer.files.Data;
import me.gonzyui.zoneclaimer.utils.ChunkInfo;
import me.gonzyui.zoneclaimer.utils.ClaimUtils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Claims implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Chunk chunk = e.getBlock().getChunk();
        ChunkInfo cInfo = Data.getChunkInfo(chunk);
        if (cInfo == null) {
            return;
        }
        if (!cInfo.getOwner().equals(player.getUniqueId())) {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.cant-interact-in-zone").replace("&", "§"));
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0F, 8.0F);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Chunk chunk = e.getBlock().getChunk();
        ChunkInfo cInfo = Data.getChunkInfo(chunk);
        if (cInfo == null) {
            return;
        }
        if (!cInfo.getOwner().equals(player.getUniqueId())) {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.cant-interact-in-zone").replace("&", "§"));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Chunk chunk = player.getLocation().getChunk();
        ChunkInfo cInfo = Data.getChunkInfo(chunk);

        if (cInfo == null) {
            return;
        }

        if (cInfo.getOwner().equals(player.getUniqueId())) {
            return;
        }

        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (e.getAction() != Action.PHYSICAL || e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (e.getAction() == Action.PHYSICAL) {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.cant-interact-in-zone").replace("&", "§"));
            e.setCancelled(true);
            return;
        }

        Material material = e.getClickedBlock().getType();
        if (material.toString().contains("DOOR") || material.toString().contains("CHEST")) {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.cant-interact-in-zone").replace("&", "§"));
            e.setCancelled(true);
            return;
        }

        if (cInfo.getOwner().equals(player.getUniqueId())) {
            ClaimUtils.send(player, Main.getINSTANCE().getConfig().getString("messages.cant-interact-in-zone").replace("&", "§"));
            e.setCancelled(true);
        }
    }
// New release soon
//    @EventHandler
//    public void onPlayerMoveInClaim(PlayerMoveEvent e) {
//        Player player = e.getPlayer();
//        Chunk chunk = player.getLocation().getChunk();
//        ChunkInfo cInfo = Data.getChunkInfo(chunk);
//        if (cInfo == null) {
//            return;
//        }
//
//        if (cInfo.getOwner().equals(player.getUniqueId())) {
//            if (e.getFrom().getChunk() != e.getTo().getChunk()) {
//                player.sendTitle(Main.getINSTANCE().getConfig().getString("titles.own-zone-entering.title").replace("&", "§").replace("%id%", cInfo.getId()), Main.getINSTANCE().getConfig().getString("titles.own-zone-entering.subtitle").replace("&", "§").replace("%id%", cInfo.getId()));
//            }
//        }
//
//        if (!(cInfo.getOwner().equals(player.getUniqueId()))) {
//            if (e.getFrom().getChunk() != e.getTo().getChunk()) {
//                player.sendTitle(Main.getINSTANCE().getConfig().getString("titles.other-zone-leaving.entering").replace("&", "§").replace("%id%", cInfo.getId()), Main.getINSTANCE().getConfig().getString("titles.other-zone-entering.subtitle").replace("&", "§").replace("%id%", cInfo.getId()));
//            }
//        }
//    }
}
