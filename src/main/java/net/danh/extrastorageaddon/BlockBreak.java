package net.danh.extrastorageaddon;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Random;

public class BlockBreak implements Listener {

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onStoring(ItemStoringEvent e) {
        Player p = e.getUser().getPlayer();
        if (p.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            int amount = getRandomInt(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) * 5);
            StorageAPI.getInstance().getUser(e.getUser().getUUID()).getStorage().addMaterial(e.getItem(), amount);
        }
    }
}
