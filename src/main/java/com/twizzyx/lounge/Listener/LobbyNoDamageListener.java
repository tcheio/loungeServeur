package com.twizzyx.lounge.Listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class LobbyNoDamageListener implements Listener {

    private final String lobbyWorldName;

    public LobbyNoDamageListener(String lobbyWorldName) {
        this.lobbyWorldName = lobbyWorldName;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        World w = player.getWorld();
        if (w == null) return;

        if (w.getName().equalsIgnoreCase(lobbyWorldName)) {
            event.setCancelled(true);
            // Optionnel : force la vie au max si tu veux éviter des effets bizarres
            // player.setFireTicks(0);
        }
    }

    // Petit helper optionnel si tu veux réutiliser ailleurs
    public boolean isInLobby(Player player) {
        return player != null
                && player.getWorld() != null
                && player.getWorld().getName().equalsIgnoreCase(lobbyWorldName);
    }
}
