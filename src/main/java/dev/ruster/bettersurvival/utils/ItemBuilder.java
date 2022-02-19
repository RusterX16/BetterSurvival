package dev.ruster.bettersurvival.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;
    private final Map<Enchantment, Integer> enchantments;
    private Material material;
    private String name;
    private List<String> lore;
    private int amount;
    private short durability;
    private boolean flags;
    private boolean unbreakable;

    public ItemBuilder(@NotNull ItemBuilder builder) {
        this(builder.material);
    }

    public ItemBuilder(Material material) {
        this.material = material;
        item = new ItemStack(material);
        lore = new ArrayList<>();
        enchantments = new HashMap<>();
        meta = item.getItemMeta();
        amount = item.getAmount();
        flags = false;
        unbreakable = true;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder displayName(String name) {
        this.name = name;
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder colorName(ChatColor color) {
        if(name == null) {
            throw new NullPointerException("Display Name from " + this.getClass() + " is null");
        }
        displayName(color + name);
        return this;
    }

    public ItemBuilder colorName(@NotNull String code) {
        if(name == null) {
            throw new NullPointerException("Display Name from " + this.getClass() + " is null");
        }
        if(!code.startsWith("§a") && code.length() != 2) {
            throw new IllegalArgumentException("Bad syntax of code : " + code);
        }
        displayName(code + name);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder lore(List<String> lore) {
        this.lore.addAll(lore);
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder lore(String... lines) {
        lore.addAll(Arrays.asList(lines));
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder append(String line) {
        lore.add(line);
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder insert(int index, String line, boolean override) {
        if(!override) {
            append("");

            for(int i = lore.size() - 1; i > index; i--) {
                lore.set(i, lore.get(i - 1));
            }
        }
        lore.set(index, line);
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder modify(int index, String text) {
        if(lore.get(index) == null) {
            throw new NullPointerException("Index " + index + " in lore of " + this.getClass() + " does not exist");
        }
        lore.set(index, text);
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder remove(int index, boolean tighten) {
/*        if(tighten) {
            for(int i = lore.size() - 1; i >= index; i--) {
                lore.set(i, lore.get(i - 1));
            }
        }*/
        lore.remove(index);
        meta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder clear() {
        lore.clear();
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder enchant(@NotNull Map<Enchantment, Integer> enchantments) {
        this.enchantments.putAll(enchantments);
        enchantments.forEach((k, v) -> meta.addEnchant(k, v, true));
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder disenchant(Enchantment enchantment) {
        enchantments.remove(enchantment);
        meta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder damage(short damage) {
        this.durability = damage;

        if(meta instanceof Damageable d) {
            d.setDamage(damage);
        }
        return this;
    }

    public ItemBuilder dye(Color color) {
        if(meta instanceof LeatherArmorMeta lam) {
            lam.setColor(color);
        }
        return this;
    }

    public ItemBuilder spawnType(CreatureSpawner creatureSpawner) {
        if(meta instanceof BlockStateMeta bsm) {
            bsm.setBlockState(creatureSpawner);
        }
        return this;
    }

    public ItemBuilder flags(boolean flags) {
        this.flags = flags;

        if(flags) {
            meta.addItemFlags(ItemFlag.values());
        } else {
            meta.removeItemFlags(ItemFlag.values());
        }
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public int getAmount() {
        return amount;
    }

    public short getDurability() {
        return durability;
    }

    public boolean hasFlags() {
        return flags;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }
}