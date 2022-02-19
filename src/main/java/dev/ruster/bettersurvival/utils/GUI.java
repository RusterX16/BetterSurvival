package dev.ruster.bettersurvival.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUI {

    private final Inventory inventory;
    private final String title;
    private final int size;
    private Player player;

    public GUI(String title, int row, Player player) {
        this(title, row);
        this.player = player;
    }

    public GUI(String title, int row) {
        inventory = Bukkit.createInventory(null, row * 9, title);
        this.title = title;
        size = row * 9;
    }

    public void add(ItemStack... item) {
        inventory.addItem(item);
    }

    public GUI set(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    public void open() {
        if(player == null) {
            throw new NullPointerException("Player is null");
        }
        open(player);
    }

    public void open(@NotNull Player player) {
        player.openInventory(inventory);
    }

    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public void clear() {
        inventory.clear();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public ItemStack get(int slot) {
        return inventory.getItem(slot);
    }
}
