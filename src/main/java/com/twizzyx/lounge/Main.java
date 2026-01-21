package com.twizzyx.lounge;

import com.twizzyx.lounge.Commands.BypassBuildCommand;
import com.twizzyx.lounge.Commands.LobbyCommand;
import com.twizzyx.lounge.Listener.*;
import com.twizzyx.lounge.utils.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        BuildProtectionListener buildProtection = new BuildProtectionListener();

        //Enregistrement Listener
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(buildProtection, this);
        getServer().getPluginManager().registerEvents(new MiniJeuItemListener(), this);
        getServer().getPluginManager().registerEvents(new AntiFallListener(), this);
        getServer().getPluginManager().registerEvents(new LobbyNoDamageListener("lobbyGeneral"), this);
        Bukkit.getScheduler().runTaskTimer(this, TablistManager::updateTabForAll, 0L, 100L);


        //Commands serveur
        getCommand("bypassbuild").setExecutor(new BypassBuildCommand(buildProtection));
        getCommand("lobby").setExecutor(new LobbyCommand());
        getLogger().info("Le plugin est activé !");
    }


    @Override
    public void onDisable() {
        getLogger().info("Le plugin est désactivé !");
    }
}
