package net.danh.extrastorageaddon.Manager;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.data.user.User;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
                        if (ExtraStorageAddon.getInstance().getEManager().checkBlock(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack).getType())) {
                            try {
                                if (extraStorageAddon.getEManager().checkFlags(e.getPlayer(), location.getBlock())) {
                                    if (extraStorageAddon.getEManager().applyFortune(e.getPlayer(), itemStack)) {
                                        location.getBlock().setType(Material.AIR);
                                    } else {
                                        if (extraStorageAddon.getEManager().checkSpace(e.getPlayer(), 1)) {
                                            if (user.getStorage().isUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack))) {
                                                user.getStorage().addUnused(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), 1);
                                            } else {
                                                user.getStorage().addMaterial(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack), 1);

                                            }
                                            location.getBlock().setType(Material.AIR);
                                        }
                                    }
                                }
                            } catch (NullPointerException ex) {
                                user.getStorage().addNewMaterial(extraStorageAddon.getEManager().getItem(e.getPlayer(), itemStack));
                            }
                        }
                    }
                }
            }
        }
    }
}