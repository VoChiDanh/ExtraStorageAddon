package net.danh.extrastorageaddon;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class ExtraStorageAddon extends JavaPlugin implements Listener {

    private static ExtraStorageAddon instance;

    public static ExtraStorageAddon getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        if (getServer().getPluginManager().getPlugin("ExtraStorage") != null) {
            getServer().getPluginManager().registerEvents(new BlockBreak(), this);
            getLogger().log(Level.INFO, "Successfully hooked with ExtraStorage v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("ExtraStorage")).getDescription().getVersion());
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
        Objects.requireNonNull(getCommand("exaddon")).setExecutor(new Cmd());
        Files.createconfig();
    }

    @Override
    public void onDisable() {
        Files.saveconfig();
    }

}
