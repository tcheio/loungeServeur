package com.twizzyx.lounge.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location spawnCoord = new Location(Bukkit.getWorld("Lobby"), 31.5, 177, 56.5, 180f, 0f);
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande est réservée aux joueurs !");
            return true;
        }

        Player player = (Player) sender;
        World lobbyWorld = (Bukkit.getWorld("Lobby"));

        if (lobbyWorld == null) {
            player.sendMessage("§cLe monde principal n'est pas disponible.");
            return true;
        }

        player.teleport(spawnCoord);
        player.sendMessage("§aTéléportation vers le lobby !");
        return true;
    }
}
