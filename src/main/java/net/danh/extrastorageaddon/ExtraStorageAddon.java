package net.danh.extrastorageaddon;

import net.danh.extrastorageaddon.CMD.CMD;
import net.danh.extrastorageaddon.Enchants.EnchantWrapper;
import net.danh.extrastorageaddon.Events.BlockBreak;
import net.danh.extrastorageaddon.Manager.Files;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

import static net.danh.extrastorageaddon.Manager.EManager.register;

public final class ExtraStorageAddon extends JavaPlugin implements Listener {

    public final static Enchantment EXPLOSIVE = new EnchantWrapper(200, "Explosive", 100, EnchantmentTarget.TOOL);
    public final static Enchantment SMELT = new EnchantWrapper(201, "Smelt", 100, EnchantmentTarget.TOOL);

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
            register();
        } else {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        new CMD(this, "exaddon");
        Files.createconfig();
    }

    @Override
    public void onDisable() {
        Files.saveconfig();
    }


}
