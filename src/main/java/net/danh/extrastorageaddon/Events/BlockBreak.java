package net.danh.extrastorageaddon.Events;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import net.danh.extrastorageaddon.ExtraStorageAddon;
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

    private final ExtraStorageAddon extraStorageAddon;
    private final StorageAPI storageAPI;

    public BlockBreak(ExtraStorageAddon extraStorageAddon, StorageAPI storageAPI) {
        this.extraStorageAddon = extraStorageAddon;
        this.storageAPI = storageAPI;
    }

    @EventHandler
    public void onStorage(ItemStoringEvent e) {
        if (extraStorageAddon.getEManager().getItem(e.getUser().getPlayer(), e.getItem()) != e.getItem()) {
            storageAPI.getUser(e.getUser().getUUID()).getStorage().addMaterial(extraStorageAddon.getEManager().getItem(e.getUser().getPlayer(), e.getItem()), e.getItem().getAmount());
            storageAPI.getUser(e.getUser().getUUID()).getStorage().subtractMaterial(e.getItem(), e.getItem().getAmount());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        List<String> w = Files.getconfigfile().getStringList("disable-worlds");
        if (!(w.contains(p.getWorld().getName()))) {
            if (ExtraStorageAddon.getInstance().getEManager().checkFlags(p, e.getBlock())) {
                if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                    for (ItemStack itemStack : e.getBlock().getDrops()) {
                        extraStorageAddon.getEManager().applyFortune(p, itemStack);
                    }
                }
                if (p.getInventory().getItemInMainHand().containsEnchantment(extraStorageAddon.EXPLOSIVE)) {
                    if (new Random().nextInt(100) < Files.getconfigfile().getInt("enchants.explosive.chance." + p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE))) {
                        new ExplosiveBlock(extraStorageAddon).run(e, p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE));
                    }
                }
            }
        }
    }
}