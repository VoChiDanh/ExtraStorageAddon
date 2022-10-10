package net.danh.extrastorageaddon.Events;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
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
import java.util.Objects;
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
            if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                for (ItemStack itemStack : e.getBlock().getDrops()) {
                    if (storageAPI.getUser(p.getUniqueId()).getStorage().canStore(extraStorageAddon.getEManager().getItem(p, itemStack), false)) {
                        String fortune = Objects.requireNonNull(Files.getconfigfile().getString("enchants.fortune", "#level# * 5")).replace("#level#", String.valueOf(e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)));
                        fortune = PlaceholderAPI.setPlaceholders(p, fortune);
                        int amount = (int) Double.parseDouble(Calculator.calculator(fortune, 0));
                        /*     if (storageAPI.getUser(p.getUniqueId()).getStorage().getFreeSpace() - (long) (storageAPI.getUser(p.getUniqueId()).getStorage().getMaterial(itemStack) + amount) >= 0) {*/
                        if (storageAPI.getUser(p.getUniqueId()).getStorage().isUnused(extraStorageAddon.getEManager().getItem(p, itemStack))) {
                            storageAPI.getUser(p.getUniqueId()).getStorage().addUnused(extraStorageAddon.getEManager().getItem(p, itemStack), amount);
                        } else {
                            storageAPI.getUser(p.getUniqueId()).getStorage().addMaterial(extraStorageAddon.getEManager().getItem(p, itemStack), amount);
                        }
                        /*}*/
                    }
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