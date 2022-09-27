package net.danh.extrastorageaddon.CMD;

import net.danh.dcore.Commands.CMDBase;
import net.danh.extrastorageaddon.ExtraStorageAddon;
import net.danh.extrastorageaddon.Manager.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
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
                    ExtraStorageAddon.getEManager().enchantItems(p, args[1], Integer.parseInt(args[2]));
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
                ExtraStorageAddon.getEManager().enchantItems(p, args[1], Integer.parseInt(args[2]));
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
