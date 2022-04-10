package net.danh.extrastorageaddon;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("exaddon")) {
            if (args.length == 0) {
                Files.reloadfiles();
                sender.sendMessage("Reloaded!");
                return true;
            }
        }
        return false;
    }
}
