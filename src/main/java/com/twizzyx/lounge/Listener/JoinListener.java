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
        player.setLevel(0);
        player.setExp(0f);
        ScoreboardManagerLounge.setScoreboard(player);
        // Téléportation au spawn
        Location spawn = new Location(Bukkit.getWorld("world"), 31, 177, 56);
        player.teleport(spawn);

        // Message de bienvenue en title
        player.sendTitle("Bienvenue sur", "§5LE LOUNGE", 10, 60, 10);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eLounge");
        item.setItemMeta(meta);
        player.getInventory().setItem(8, item);

        TablistManager.updateTab(player);
    }
}
