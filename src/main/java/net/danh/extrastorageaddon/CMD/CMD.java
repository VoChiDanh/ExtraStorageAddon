package net.danh.extrastorageaddon.CMD;

import net.danh.dcore.Commands.CMDBase;
import net.danh.dcore.Utils.Chat;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import net.danh.extrastorageaddon.Manager.Files;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class CMD extends CMDBase {

    public CMD(JavaPlugin core, String name) {
        super(core, name);
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("extraaddon.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Files.reloadfiles();
                    sendPlayerMessage(p, "&aReloaded");
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("enchant")) {
                    if (args[1].equalsIgnoreCase("explosive")) {
                        int level = Integer.parseInt(args[2]);
                        if (Files.getconfigfile().getBoolean("enchants.explosive.custom_lore")) {
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                        } else {
                            if (p.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                meta.setLore(Collections.singletonList(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2])));
                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                                sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                            } else {
                                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                List<String> item_lore = meta.getLore();
                                for (int i = 0; i <= item_lore.size(); i++) {
                                    if (item_lore.contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE))))) {
                                        if (item_lore.get(i).contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE))))) {
                                            item_lore.set(i, ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2]));
                                            meta.setLore(item_lore);
                                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                                            break;
                                        }
                                    } else {
                                        item_lore.add(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2]));
                                        meta.setLore(item_lore);
                                        p.getInventory().getItemInMainHand().setItemMeta(meta);
                                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (args[1].equalsIgnoreCase("smelt")) {
                        int level = Integer.parseInt(args[2]);
                        if (Files.getconfigfile().getBoolean("enchants.smelt.custom_lore")) {
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                        } else {
                            if (p.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                meta.setLore(Collections.singletonList(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2])));
                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                                sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                            } else {
                                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                                List<String> item_lore = meta.getLore();
                                for (int i = 0; i <= item_lore.size(); i++) {
                                    if (item_lore.contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT))))) {
                                        if (item_lore.get(i).contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT))))) {
                                            item_lore.set(i, ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2]));
                                            meta.setLore(item_lore);
                                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                                            break;
                                        }
                                    } else {
                                        item_lore.add(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2]));
                                        meta.setLore(item_lore);
                                        p.getInventory().getItemInMainHand().setItemMeta(meta);
                                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Files.reloadfiles();
                sendConsoleMessage(c, "&aReloaded");
            }
        }
        if (args.length == 4) {
            Player p = Bukkit.getPlayer(args[3]);
            if (p == null) return;
            if (args[0].equalsIgnoreCase("enchant")) {
                if (args[1].equalsIgnoreCase("explosive")) {
                    int level = Integer.parseInt(args[2]);
                    if (Files.getconfigfile().getBoolean("enchants.explosive.custom_lore")) {
                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                    } else {
                        if (p.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                            meta.setLore(Collections.singletonList(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2])));
                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                        } else {
                            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                            List<String> item_lore = meta.getLore();
                            for (int i = 0; i <= item_lore.size(); i++) {
                                if (item_lore.contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE))))) {
                                    if (item_lore.get(i).contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE))))) {
                                        item_lore.set(i, ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2]));
                                        meta.setLore(item_lore);
                                        p.getInventory().getItemInMainHand().setItemMeta(meta);
                                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                                        break;
                                    }
                                } else {
                                    item_lore.add(ChatColor.GRAY + Files.getconfigfile().getString("enchants.explosive.name").replaceAll("#level#", args[2]));
                                    meta.setLore(item_lore);
                                    p.getInventory().getItemInMainHand().setItemMeta(meta);
                                    p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.EXPLOSIVE, level);
                                    sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.explosive.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.EXPLOSIVE)))));
                                    break;
                                }
                            }
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("smelt")) {
                    int level = Integer.parseInt(args[2]);
                    if (Files.getconfigfile().getBoolean("enchants.smelt.custom_lore")) {
                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                    } else {
                        if (p.getInventory().getItemInMainHand().getItemMeta().getLore() == null) {
                            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                            meta.setLore(Collections.singletonList(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2])));
                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                            sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                        } else {
                            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                            List<String> item_lore = meta.getLore();
                            for (int i = 0; i <= item_lore.size(); i++) {
                                if (item_lore.contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT))))) {
                                    if (item_lore.get(i).contains(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT))))) {
                                        item_lore.set(i, ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2]));
                                        meta.setLore(item_lore);
                                        p.getInventory().getItemInMainHand().setItemMeta(meta);
                                        p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                                        sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                                        break;
                                    }
                                } else {
                                    item_lore.add(ChatColor.GRAY + Files.getconfigfile().getString("enchants.smelt.name").replaceAll("#level#", args[2]));
                                    meta.setLore(item_lore);
                                    p.getInventory().getItemInMainHand().setItemMeta(meta);
                                    p.getInventory().getItemInMainHand().addUnsafeEnchantment(ExtraStorageAddon.SMELT, level);
                                    sendPlayerMessage(p, Chat.colorize(Files.getconfigfile().getString("enchants.smelt.add").replaceAll("#level#", String.valueOf(p.getInventory().getItemInMainHand().getEnchantmentLevel(ExtraStorageAddon.SMELT)))));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (sender.hasPermission("extraaddon.admin")) {
            if (args.length == 1) {
                commands.add("enchant");
                commands.add("reload");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("enchant")) {
                    commands.add("explosive");
                    commands.add("smelt");
                    StringUtil.copyPartialMatches(args[1], commands, completions);
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
