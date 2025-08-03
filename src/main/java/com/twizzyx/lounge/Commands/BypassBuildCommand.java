package com.twizzyx.lounge.Commands;

import com.twizzyx.lounge.Listener.BuildProtectionListener;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassBuildCommand implements CommandExecutor {

    private final BuildProtectionListener listener;

    public BypassBuildCommand(BuildProtectionListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage("§cTu n’as pas la permission.");
            return true;
        }

        listener.toggleBypass(player);
        return true;
    }
}
