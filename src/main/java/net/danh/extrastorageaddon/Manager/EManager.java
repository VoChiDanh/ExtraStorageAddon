package net.danh.extrastorageaddon.Manager;

import net.danh.extrastorageaddon.ExtraStorageAddon;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EManager {

    public static ItemStack getItem(Player p, ItemStack item) {
        if (p.getInventory().getItemInMainHand().containsEnchantment(ExtraStorageAddon.SMELT)) {
            if (Files.getconfigfile().getString("enchants.smelt.blocks." + item.getType().name()) != null) {
                return new ItemStack(Material.getMaterial(Files.getconfigfile().getString("enchants.smelt.blocks." + item.getType().name())));
            } else {
                return item;
            }
        } else {
            return item;
        }
    }

    public static List<Enchantment> getCustomEnchants() {
        List<Enchantment> list = new ArrayList<>();
        list.add(ExtraStorageAddon.EXPLOSIVE);
        list.add(ExtraStorageAddon.SMELT);
        return list;
    }

    public static void register() {
        for (Enchantment enchantment : getCustomEnchants()) {
            if (!(Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment))) {
                registerEnchantment(enchantment);
            }
        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
