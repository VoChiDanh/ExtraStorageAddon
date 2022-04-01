package net.danh.extrastorageaddon;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class ExtraStorageAddon extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("ExtraStorage") != null) {
            getServer().getPluginManager().registerEvents(new BlockBreak(), this);
            getLogger().log(Level.INFO, "Successfully hooked with ExtraStorage v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("ExtraStorage")).getDescription().getVersion());
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with WorldGuard v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("WorldGuard")).getDescription().getVersion());
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
