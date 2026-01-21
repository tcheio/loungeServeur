package com.twizzyx.lounge.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiFallListener implements Listener {

    @EventHandler
    public void onFall(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Vérifie le monde concerné uniquement
        if (!player.getWorld().getName().equalsIgnoreCase("lobbyGeneral")) return;

        // Vérifie si le joueur descend sous Y = 140
        if (player.getLocation().getY() < 140) {
            World world = Bukkit.getWorld("lobbyGeneral");
            if (world == null) return;

            Location spawn = new Location(world, 31.5, 177, 56.5, 180f, 0f);
            player.teleport(spawn);

            // ✅ Annule les dégâts de chute
            player.setFallDistance(0f);

            player.sendMessage("§eTu as été ramené au spawn !");
        }
    }
}
