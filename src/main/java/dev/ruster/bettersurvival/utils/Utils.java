package dev.ruster.bettersurvival.utils;

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
}
