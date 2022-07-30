package net.danh.extrastorageaddon;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Random;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onStoring(ItemStoringEvent e) {
        Player p = e.getUser().getPlayer();
        List<String> w = Files.getconfigfile().getStringList("disable-worlds");
        if (!(w.contains(p.getWorld().getName()))) {
            if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                StorageAPI.getInstance().getUser(p.getUniqueId()).getStorage().addMaterial(e.getItem(), new Random().nextInt(p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) * 2);
            }
        }
    }
}