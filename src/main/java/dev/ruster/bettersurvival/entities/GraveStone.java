package dev.ruster.bettersurvival.entities;

import dev.ruster.bettersurvival.utils.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class GraveStone {

    public static final List<GraveStone> GRAVE_STONES = new ArrayList<>();

    private final Player player;
    private GUI gui;

    public GraveStone(Player player) {
        this.player = player;
        gui = new GUI("§8Inventaire de " + player.getName(), 4, player);
        GRAVE_STONES.add(this);
        initGUI();
    }

    public static GraveStone getFromItemStack(ItemStack item) {
        Optional<GraveStone> op = Optional.empty();

        for(GraveStone gs : GRAVE_STONES) {
            if(gs.get)
        }
    }

    public void initGUI() {
        ItemStack[] stuff = player.getInventory().getContents();
        IntStream.range(0, player.getInventory().getContents().length)
                .filter(i -> stuff[i] != null && stuff[i].getType() != Material.AIR)
                .forEach(i -> gui.set(i, stuff[i]));
    }

    public Player getPlayer() {
        return player;
    }

    public GUI getGUI() {
        return gui;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
