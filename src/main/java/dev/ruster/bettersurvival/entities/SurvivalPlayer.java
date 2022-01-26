package dev.ruster.bettersurvival.entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurvivalPlayer {

    public static final List<SurvivalPlayer> PLAYERS = new ArrayList<>();

    private final Player player;
    private final List<GraveStone> graveStones;

    public SurvivalPlayer(Player player) {
        this.player = player;
        graveStones = new ArrayList<>();

        PLAYERS.add(this);
    }

    public static SurvivalPlayer getPlayerFromBukkitPlayer(Player player) {
        Optional<SurvivalPlayer> op = Optional.empty();

        for(SurvivalPlayer sp : PLAYERS) {
            if(sp.player == player) {
                op = Optional.of(sp);
            }
        }
        return op.orElse(null);
    }

    public static SurvivalPlayer find(Player player) {
        Optional<SurvivalPlayer> op = Optional.empty();

        for(SurvivalPlayer sp : PLAYERS) {
            if(sp.player == player) {
                op = Optional.of(sp);
            }
        }
        return op.orElse(new SurvivalPlayer(player));
    }

    public void createGraveStone(@NotNull Location location) {
        new GraveStone(this, new Location(location.getWorld(),
                Math.round(location.getX()),
                Math.round(location.getY()),
                Math.round(location.getZ())));
    }

    public void set(int slot, ItemStack item) {
        player.getInventory().setItem(slot, item);
    }

    public void openInventory(Inventory inventory) {
        player.openInventory(inventory);
    }

    public @Nullable ItemStack helmet() {
        return getInventory().getHelmet();
    }

    public void helmet(ItemStack item) {
        getInventory().setHelmet(item);
    }

    public @Nullable ItemStack chestplate() {
        return getInventory().getChestplate();
    }

    public void chestplate(ItemStack item) {
        getInventory().setChestplate(item);
    }

    public @Nullable ItemStack leggings() {
        return getInventory().getLeggings();
    }

    public void leggings(ItemStack item) {
        getInventory().setLeggings(item);
    }

    public @Nullable ItemStack boots() {
        return getInventory().getBoots();
    }

    public void boots(ItemStack item) {
        getInventory().setBoots(item);
    }

    public Player getPlayer() {
        return player;
    }

    public List<GraveStone> getGraveStones() {
        return graveStones;
    }

    public String getName() {
        return player.getName();
    }

    public @NotNull PlayerInventory getInventory() {
        return player.getInventory();
    }

    public Location getLocation() {
        return player.getLocation();
    }

    public @NotNull World world() {
        return player.getWorld();
    }

    public void level(int level) {
        player.setLevel(player.getLevel() + level);
    }

    public void exp(float exp) {
        player.setExp(Math.min(1, player.getExp() + exp));
    }
}
