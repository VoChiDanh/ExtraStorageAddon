package net.danh.extrastorageaddon.Manager;

import com.hyronic.exstorage.api.StorageAPI;
import com.hyronic.exstorage.data.user.Storage;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
import net.danh.dcore.Utils.Chat;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class EManager {

    private final ExtraStorageAddon extraStorageAddon;
    private final StorageAPI storageAPI;


    public EManager(ExtraStorageAddon extraStorageAddon, StorageAPI storageAPI) {
        this.extraStorageAddon = extraStorageAddon;
        this.storageAPI = storageAPI;
    }

    public void enchantItems(Player p, String enchant, int level) {
        if (enchant.equalsIgnoreCase("explosive")) {
            if (Files.getconfigfile().getBoolean("enchants.explosive.custom_lore")) {
                p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.EXPLOSIVE, level);
                sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE)))));
            } else {
                if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getLore() == null) {
                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                    meta.setLore(Collections.singletonList(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.name")).replaceAll("#level#", String.valueOf(level))));
                    p.getInventory().getItemInMainHand().setItemMeta(meta);
                    p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.EXPLOSIVE, level);
                    sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE)))));
                } else {
                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                    List<String> item_lore = meta.getLore();
                    for (int i = 0; i <= item_lore.size(); i++) {
                        if (item_lore.contains(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.name")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE))))) {
                            if (item_lore.get(i).contains(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.name")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE))))) {
                                item_lore.set(i, ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.name")).replaceAll("#level#", String.valueOf(level)));
                                meta.setLore(item_lore);
                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.EXPLOSIVE, level);
                                sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE)))));
                                break;
                            }
                        } else {
                            item_lore.add(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.name")).replaceAll("#level#", String.valueOf(level)));
                            meta.setLore(item_lore);
                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.EXPLOSIVE, level);
                            sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.explosive.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.EXPLOSIVE)))));
                            break;
                        }
                    }
                }
            }
        }
        if (enchant.equalsIgnoreCase("smelt")) {
            if (Files.getconfigfile().getBoolean("enchants.smelt.custom_lore")) {
                p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.SMELT, level);
                sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT)))));
            } else {
                if (Objects.requireNonNull(p.getInventory().getItemInMainHand().getItemMeta()).getLore() == null) {
                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                    meta.setLore(Collections.singletonList(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.name")).replaceAll("#level#", String.valueOf(level))));
                    p.getInventory().getItemInMainHand().setItemMeta(meta);
                    p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.SMELT, level);
                    sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT)))));
                } else {
                    ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                    List<String> item_lore = meta.getLore();
                    for (int i = 0; i <= item_lore.size(); i++) {
                        if (item_lore.contains(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.name")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT))))) {
                            if (item_lore.get(i).contains(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.name")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT))))) {
                                item_lore.set(i, ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.name")).replaceAll("#level#", String.valueOf(level)));
                                meta.setLore(item_lore);
                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.SMELT, level);
                                sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT)))));
                                break;
                            }
                        } else {
                            item_lore.add(ChatColor.GRAY + Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.name")).replaceAll("#level#", String.valueOf(level)));
                            meta.setLore(item_lore);
                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(extraStorageAddon.SMELT, level);
                            sendPlayerMessage(p, Chat.colorize(Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.add")).replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(extraStorageAddon.SMELT)))));
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean checkFlags(Player p, Block block) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        com.sk89q.worldedit.util.Location loc = new com.sk89q.worldedit.util.Location(localPlayer.getWorld(), block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        return query.testState(loc, localPlayer, Flags.BLOCK_BREAK) && p.getGameMode().equals(GameMode.SURVIVAL);
    }

    public ItemStack getItem(Player p, ItemStack item) {
        if (p.getInventory().getItemInMainHand().containsEnchantment(extraStorageAddon.SMELT)) {
            if (Files.getconfigfile().getString("enchants.smelt.blocks." + item.getType().name()) != null) {
                return new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Files.getconfigfile().getString("enchants.smelt.blocks." + item.getType().name())))));
            } else {
                return item;
            }
        } else {
            return item;
        }
    }

    public List<Enchantment> getCustomEnchants() {
        List<Enchantment> list = new ArrayList<>();
        list.add(extraStorageAddon.EXPLOSIVE);
        list.add(extraStorageAddon.SMELT);
        return list;
    }

    public void register() {
        for (Enchantment enchantment : getCustomEnchants()) {
            if (!(Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment))) {
                registerEnchantment(enchantment);
            }
        }
    }

    public void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlock(Material m) {
        return Files.getconfigfile().getStringList("enchants.explosive.blocks").contains(m.name());
    }

    public boolean checkSpace(Player p, int amount) {
        Storage user = StorageAPI.getInstance().getUser(p.getUniqueId()).getStorage();
        if (user.getFreeSpace() > 0) {
            return user.getFreeSpace() - amount >= 0;
        } else {
            return false;
        }
    }

    public boolean applyFortune(Player p, ItemStack itemStack) {
        if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            try {
                String fortune = Objects.requireNonNull(Files.getconfigfile().getString("enchants.fortune", "#level# * 5")).replace("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)));
                fortune = PlaceholderAPI.setPlaceholders(p, fortune);
                int amount = (int) Double.parseDouble(Calculator.calculator(fortune, 0));
                if (extraStorageAddon.getEManager().checkSpace(p, amount)) {
                    if (storageAPI.getUser(p.getUniqueId()).getStorage().isUnused(extraStorageAddon.getEManager().getItem(p, itemStack))) {
                        storageAPI.getUser(p.getUniqueId()).getStorage().addUnused(extraStorageAddon.getEManager().getItem(p, itemStack), amount);
                    } else {
                        storageAPI.getUser(p.getUniqueId()).getStorage().addMaterial(extraStorageAddon.getEManager().getItem(p, itemStack), amount);
                    }
                    return true;
                }
            } catch (NullPointerException ex) {
                storageAPI.getUser(p.getUniqueId()).getStorage().addNewMaterial(extraStorageAddon.getEManager().getItem(p, itemStack));
                return applyFortune(p, itemStack);
            }
        }
        return false;
    }
}
