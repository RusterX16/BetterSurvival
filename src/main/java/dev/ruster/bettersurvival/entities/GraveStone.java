package dev.ruster.bettersurvival.entities;

import dev.ruster.bettersurvival.utils.GUI;
import dev.ruster.bettersurvival.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class GraveStone {

    public static final List<GraveStone> GRAVE_STONE_LIST = new ArrayList<>();

    private final Player player;
    private final Location location;
    private final ItemBuilder expItem;
    private final int level;
    private final float exp;
    private GUI gui;

    public GraveStone(@NotNull Player player, @NotNull Location location) {
        GRAVE_STONE_LIST.add(this);
        this.player = player;
        this.location = location;
        expItem = new ItemBuilder(Material.EXPERIENCE_BOTTLE).displayName("§aExpérience perdue ").flags(true);
        gui = new GUI("§8Mort de " + player.getName(), 5, player);
        level = player.getLevel();
        exp = player.getExp();

        location.getBlock().setType(Material.SKELETON_SKULL);
        initGUI();
    }

    public void initGUI() {
        ItemStack[] stuff = player.getInventory().getContents();
        ItemStack[] armor = player.getInventory().getArmorContents();

        IntStream.range(0, gui.getSize() - 9)
                .filter(i -> stuff[i] != null && stuff[i].getType() != Material.AIR)
                .forEach(i -> gui.set(i, stuff[i]));
        IntStream.range(gui.getSize() - 9, gui.getSize() - 5)
                .filter(i -> armor[i - 36] != null && armor[i - 36].getType() != Material.AIR)
                .forEach(i -> gui.set(i, armor[i - 36]));
        gui.set(gui.getSize() - 4, player.getInventory().getItemInOffHand()).set(gui.getSize() - 3, expItem.build());
    }

    public void drop(Block block) {
        ItemStack[] content = gui.getInventory().getContents();
        Arrays.stream(content)
                .filter(item -> item != null && item.getType() != Material.AIR)
                .forEach(item -> block.getWorld().dropItemNaturally(block.getLocation(), item));
    }

    public Player getPlayer() {
        return player;
    }

    public Location getLocation() {
        return location;
    }

    public ItemBuilder getExpItem() {
        return expItem;
    }

    public GUI getGUI() {
        return gui;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public int getLevel() {
        return level;
    }

    public float getExp() {
        return exp;
    }
}
