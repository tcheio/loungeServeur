package com.twizzyx.lounge;

import com.twizzyx.lounge.Commands.BypassBuildCommand;
import com.twizzyx.lounge.Listener.BuildProtectionListener;
import com.twizzyx.lounge.Listener.InteractListener;
import com.twizzyx.lounge.Listener.JoinListener;
import com.twizzyx.lounge.utils.TablistManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        BuildProtectionListener buildProtection = new BuildProtectionListener();

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(buildProtection, this);
        getCommand("bypassbuild").setExecutor(new BypassBuildCommand(buildProtection));
        Bukkit.getScheduler().runTaskTimer(this, TablistManager::updateTabForAll, 0L, 100L); // toutes les 5s

        getLogger().info("Le plugin est activé !");
    }


    @Override
    public void onDisable() {
        getLogger().info("Le plugin est désactivé !");
    }
}
