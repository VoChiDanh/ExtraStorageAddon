package net.danh.extrastorageaddon;

import com.hyronic.exstorage.ExtraStorage;
import com.hyronic.exstorage.api.events.ItemStoringEvent;
import com.hyronic.exstorage.data.user.User;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BlockBreak implements Listener {

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreaking(@NotNull BlockBreakEvent e) {
        Player p = e.getPlayer();
        User user = ExtraStorage.getInstance().getUserCache().getUser(p.getUniqueId());
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        Location loc = new com.sk89q.worldedit.util.Location(localPlayer.getWorld(), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        if (!query.testState(loc, localPlayer, Flags.BLOCK_BREAK) && !p.hasPermission("bCore.admin")) {
            e.setCancelled(true);
            return;
        }
        if (p.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            int amount = getRandomInt(p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) * 10);
            ItemStack itemStack = new ItemStack(Material.valueOf(e.getBlock().getType().toString()), amount);
            ItemStoringEvent itemStoringEvent = new ItemStoringEvent(user, itemStack);
        }
    }
}
