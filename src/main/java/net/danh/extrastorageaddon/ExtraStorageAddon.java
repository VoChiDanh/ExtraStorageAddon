package net.danh.extrastorageaddon;

import com.hyronic.exstorage.api.StorageAPI;
import net.danh.extrastorageaddon.CMD.CMD;
import net.danh.extrastorageaddon.Enchants.EnchantWrapper;
import net.danh.extrastorageaddon.Events.BlockBreak;
import net.danh.extrastorageaddon.Manager.EManager;
import net.danh.extrastorageaddon.Manager.Files;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class ExtraStorageAddon extends JavaPlugin implements Listener {

    private static ExtraStorageAddon instance;
    public final Enchantment EXPLOSIVE = new EnchantWrapper("explosive", "Explosive", 100, EnchantmentTarget.TOOL);
    public final Enchantment SMELT = new EnchantWrapper("smelt", "Smelt", 100, EnchantmentTarget.TOOL);
    private final EManager eManager = new EManager(this);
    private final StorageAPI storageAPI = StorageAPI.getInstance();

    public static ExtraStorageAddon getInstance() {
        return instance;
    }

    public EManager getEManager() {
        return eManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        if (getServer().getPluginManager().getPlugin("ExtraStorage") != null) {
            getServer().getPluginManager().registerEvents(new BlockBreak(this, storageAPI), this);
            getLogger().log(Level.INFO, "Successfully hooked with ExtraStorage v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("ExtraStorage")).getDescription().getVersion());
            eManager.register();
            new CMD(this, "exaddon");
            Files.createconfig();
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Files.saveconfig();
    }


}
