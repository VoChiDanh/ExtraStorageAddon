package net.danh.extrastorageaddon.Enchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantWrapper extends EnchantmentWrapper {
    private final String name;
    private final int maxLevel;
    private final EnchantmentTarget target;
    private List<Enchantment> conflict;

    public EnchantWrapper(String nameSpace, String name, int maxLevel, EnchantmentTarget target) {
        super(nameSpace);
        this.name = name;
        this.maxLevel = maxLevel;
        this.target = target;
    }

    public EnchantWrapper(String nameSpace, String name, int maxLevel, EnchantmentTarget target, List<Enchantment> conflict) {
        this(nameSpace, name, maxLevel, target);
        this.conflict = new ArrayList<>(conflict);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return target;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (this.conflict == null) return false;
        return this.conflict.contains(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }
}
