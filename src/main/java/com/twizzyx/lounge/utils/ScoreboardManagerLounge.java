package com.twizzyx.lounge.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardManagerLounge {

    public static void setScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("lounge", "dummy", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "LE LOUNGE");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Scoreboard stylé
        objective.getScore("§8----------------").setScore(6);
        objective.getScore("§7Pseudo:").setScore(5);
        objective.getScore("§f" + player.getName()).setScore(4);
        objective.getScore("§r ").setScore(3); // ligne vide
        objective.getScore("§dProchaine soirée:").setScore(2);
        objective.getScore("§f01/01/2025").setScore(1);
        objective.getScore("§8----------------§r").setScore(0); // ligne vide
        player.setScoreboard(board);
    }
}
