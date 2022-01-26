package dev.ruster.bettersurvival.entities;

import dev.ruster.bettersurvival.utils.GUI;
import dev.ruster.bettersurvival.utils.ItemBuilder;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class GraveStone {

    private final SurvivalPlayer player;
    private final Location location;
    private final ItemBuilder expItem;
    private final int level;
    private final float exp;
    private GUI gui;

    public GraveStone(@NotNull SurvivalPlayer player, @NotNull Location location) {
        List<GraveStone> list = player.getGraveStones();
        this.player = player;
        this.location = location;
        expItem = new ItemBuilder(Material.EXPERIENCE_BOTTLE).displayName("§aExpérience perdue " + list.size()).flags(true);
        gui = new GUI("§8Mort " + list.size() + " de " + player.getName(), 5, player);
        level = player.getPlayer().getLevel();
        exp = player.getPlayer().getExp();

        location.getBlock().setType(Material.SKELETON_SKULL);
        list.add(this);
        initGUI();
    }

    public static @Nullable GraveStone getByLocation(Location location) {
        for(SurvivalPlayer sp : SurvivalPlayer.PLAYERS) {
            for(GraveStone gs : sp.getGraveStones()) {
                Location loc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

                if(Utils.isSameLocation(gs.getLocation(), loc)) {
                    return gs;
                }
            }
        }
        return null;
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

    public SurvivalPlayer getPlayer() {
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
