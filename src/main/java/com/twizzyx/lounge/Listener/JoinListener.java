package com.twizzyx.lounge.Listener;

import com.twizzyx.lounge.utils.ScoreboardManagerLounge;
import com.twizzyx.lounge.utils.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Clear complet du joueur
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setMaxHealth(20.0);
        player.setLevel(0);
        player.setExp(0f);
        ScoreboardManagerLounge.setScoreboard(player);
        // Téléportation au spawn
        Location spawn = new Location(Bukkit.getWorld("lobbyGeneral"), 31.5, 177, 56.5, 180f, 0f);
        player.teleport(spawn);

        // Message de bienvenue en title
        player.sendTitle("Bienvenue sur", "§5LE LOUNGE", 10, 60, 10);

        // Boussole Mini-jeu
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName("§dMini-jeu");
        compass.setItemMeta(meta);
        player.getInventory().setItem(0, compass); // Slot 0 pour accès facile

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        meta = item.getItemMeta();
        meta.setDisplayName("§eLounge");
        item.setItemMeta(meta);
        player.getInventory().setItem(8, item);

        TablistManager.updateTab(player);
    }

    public static void simulateJoin(Player player) {
        // ✅ Réutilise toute la logique déjà en place
        PlayerJoinEvent fakeEvent = new PlayerJoinEvent(player, "re");
        new JoinListener().onJoin(fakeEvent);
        Bukkit.getLogger().info("[Lounge] Simulated join for " + player.getName());
    }

}
