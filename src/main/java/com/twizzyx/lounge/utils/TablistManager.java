package com.twizzyx.lounge.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TablistManager {

    /**
     * Applique le header/footer personnalisé à un joueur.
     *
     * @param player Le joueur ciblé
     */
    public static void updateTab(Player player) {
        Component header = Component.text("§5§l☁ LE LOUNGE ☁");
        Component footer = Component.text(
                "§7Joueurs connectés: §b" + Bukkit.getOnlinePlayers().size() +
                        "   Twitch: §5§nTwiZzyxPasSympa");

        player.sendPlayerListHeaderAndFooter(header, footer);
    }


    /**
     * Applique la mise à jour du tab à tous les joueurs connectés.
     */
    public static void updateTabForAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            updateTab(player);
        }
    }
}
