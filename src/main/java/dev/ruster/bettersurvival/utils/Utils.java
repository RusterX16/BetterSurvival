package dev.ruster.bettersurvival.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class Utils {

    public static final String LOG_IN_OUT_MESSAGE = "§f[*§f] $p §f($c§f/$m§f)";

    private Utils() {
        throw new IllegalStateException("Utility Class");
    }

    public static @NotNull ItemStack getPlayerHead(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isSameLocation(@NotNull Location loc1, @NotNull Location loc2) {
        return Math.round(loc1.getX()) == Math.round(loc2.getX()) &&
                Math.round(loc1.getY()) == Math.round(loc2.getY()) &&
                Math.round(loc1.getZ()) == Math.round(loc2.getZ());
    }

    public static @NotNull String firstLetterInCapital(@NotNull String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
