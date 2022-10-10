package net.danh.extrastorageaddon.Manager;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.data.user.User;
import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplosiveBlock {

    private final ExtraStorageAddon extraStorageAddon;

    public ExplosiveBlock(ExtraStorageAddon extraStorageAddon) {
        this.extraStorageAddon = extraStorageAddon;
    }

    public void run(BlockBreakEvent e, int level) {
        ConfigurationSection check = Files.getconfigfile().getConfigurationSection("enchants.explosive");
        User user = StorageAPI.getInstance().getUser(e.getPlayer().getUniqueId());
        if (check != null) {
            int radius = Files.getconfigfile().getInt("enchants.explosive.radius." + level);
            List<Location> locations = new ArrayList<>();
            for (int x = e.getBlock().getX() - radius; x <= e.getBlock().getX() + radius; x++) {
                for (int y = e.getBlock().getY() - radius; y <= e.getBlock().getY() + radius; y++) {
                    for (int z = e.getBlock().getZ() - radius; z <= e.getBlock().getZ() + radius; z++) {
                        locations.add(new Location(e.getBlock().getWorld(), x, y, z));
                    }
                }
            }
            for (Location location : locations) {
                for (ItemStack itemStack : location.getBlock().getDrops()) {
                    if (Files.getconfigfile().getStringList("enchants.explosive.blocks").contains(location.getBlock().getType().name())) {
                        if (user.getStorage().canStore(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), false)) {
                            if (extraStorageAddon.getEManager().checkFlags(e.getPlayer(), location.getBlock())) {
                                String fortune = Objects.requireNonNull(Files.getconfigfile().getString("enchants.fortune", "#level# * 5")).replace("#level#", String.valueOf(e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)));
                                fortune = PlaceholderAPI.setPlaceholders(e.getPlayer(), fortune);
                                if (e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                                    int amount = (int) Double.parseDouble(Calculator.calculator(fortune, 0));
                                    /*      if (user.getStorage().getFreeSpace() - (long) (user.getStorage().getMaterial(itemStack) + amount) >= 0) {*/
                                    if (user.getStorage().isUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack))) {
                                        user.getStorage().addUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), amount);
                                    } else {
                                        user.getStorage().addMaterial(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), amount);
                                    }
                                    location.getBlock().setType(Material.AIR);
                                    /*}*/
                                } else {
                                    /*if (user.getStorage().getFreeSpace() - (long) (user.getStorage().getMaterial(itemStack) + 1) >= 0) {*/
                                    if (user.getStorage().isUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack))) {
                                        user.getStorage().addUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), 1);
                                    } else {
                                        user.getStorage().addMaterial(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), 1);
                                    }
                                    location.getBlock().setType(Material.AIR);
                                    /*}*/
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
