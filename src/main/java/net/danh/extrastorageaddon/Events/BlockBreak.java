package net.danh.extrastorageaddon.Events;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import net.danh.dcore.Calculator.Calculator;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import net.danh.extrastorageaddon.Manager.EManager;
import net.danh.extrastorageaddon.Manager.ExplosiveBlock;
import net.danh.extrastorageaddon.Manager.Files;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class BlockBreak implements Listener {
    @EventHandler
    public void onStorage(ItemStoringEvent e) {
        if (EManager.getItem(e.getUser().getPlayer(), e.getItem()) != e.getItem()) {
            StorageAPI.getInstance().getUser(e.getUser().getUUID()).getStorage().addMaterial(EManager.getItem(e.getUser().getPlayer(), e.getItem()), e.getItem().getAmount());
            StorageAPI.getInstance().getUser(e.getUser().getUUID()).getStorage().subtractMaterial(e.getItem(), e.getItem().getAmount());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        List<String> w = Files.getconfigfile().getStringList("disable-worlds");
        if (!(w.contains(p.getWorld().getName()))) {
            if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                for (ItemStack itemStack : e.getBlock().getDrops()) {
                    if (StorageAPI.getInstance().getUser(p.getUniqueId()).getStorage().canStore(EManager.getItem(p, itemStack), false)) {
                        StorageAPI.getInstance().getUser(p.getUniqueId()).getStorage().addMaterial(EManager.getItem(p, itemStack), (int) Double.parseDouble(Calculator.calculator(Files.getconfigfile().getString("enchants.fortune", "#level# * 5")
                                .replace("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS))), 0)));
                    }
                }
            }
            if (p.getInventory().getItemInMainHand().containsEnchantment(ExtraStorageAddon.EXPLOSIVE)) {
                if (new Random().nextInt(100) < Files.getconfigfile().getInt("enchants.explosive.chance." + p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE))) {
                    new ExplosiveBlock().run(e, p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE));
                }
            }
        }
    }
}