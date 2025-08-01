package com.twizzyx.lounge.Listener;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MiniJeuItemListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != Material.COMPASS) return;
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasDisplayName()) return;

        String name = item.getItemMeta().getDisplayName();
        if (!name.contains("Mini-jeu")) return;

        event.setCancelled(true); // empêche comportement par défaut

        World world = Bukkit.getWorld("worldTest");
        if (world == null) {
            player.sendMessage("§eChargement du monde...");
            world = Bukkit.createWorld(new WorldCreator("worldTest"));
        }

        Location target = new Location(world, 31, 177, 56);
        player.teleport(target);
        player.sendMessage("§aBienvenue dans le mini-jeu !");
    }
}
