package com.twizzyx.lounge.Listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class BuildProtectionListener implements Listener {

    private final Set<Player> bypassPlayers = new HashSet<>();
    private final int SPAWN_PROTECTION_RADIUS = 100;

    public void toggleBypass(Player player) {
        if (bypassPlayers.contains(player)) {
            bypassPlayers.remove(player);
            player.setGameMode(GameMode.ADVENTURE); // Désactivation → aventure
            player.sendMessage("§cMode build désactivé !");
        } else {
            bypassPlayers.add(player);
            player.setGameMode(GameMode.CREATIVE); // Activation → créatif
            player.sendMessage("§aMode build activé !");
        }
    }

    private boolean isInSpawnZone(Player player) {
        World spawnWorld = Bukkit.getWorld("world");
        if (spawnWorld == null || !player.getWorld().equals(spawnWorld)) {
            return false; // Le joueur n'est pas dans le monde du spawn
        }

        return player.getLocation().distance(spawnWorld.getSpawnLocation()) <= SPAWN_PROTECTION_RADIUS;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        // Empêche de poser l'item spécial (ex: boussole)
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand.getType() == Material.COMPASS && hand.getItemMeta() != null &&
                "§eOuvre le menu !".equals(hand.getItemMeta().getDisplayName())) {
            event.setCancelled(true);
            return;
        }

        // Protège la zone si pas en bypass
        if (isInSpawnZone(player) && !bypassPlayers.contains(player)) {
            event.setCancelled(true);
            player.sendMessage("§cTu ne peux pas poser de blocs ici.");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (isInSpawnZone(player) && !bypassPlayers.contains(player)) {
            event.setCancelled(true);
            player.sendMessage("§cTu ne peux pas casser de blocs ici.");
        }
    }
}
