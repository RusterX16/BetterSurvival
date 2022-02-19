package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public record PlayerItemHeldListener(Main main) implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerItemHeld(@NotNull PlayerItemHeldEvent e) {
        Player player = e.getPlayer();

        ItemStack main = player.getInventory().getItemInMainHand();
        ItemStack off = player.getInventory().getItemInOffHand();

        if(main.getType() == Material.COMPASS || off.getType() == Material.COMPASS) {
            if((main.hasItemMeta() || off.hasItemMeta()) &&
                    (main.getItemMeta().hasDisplayName() || off.getItemMeta().hasDisplayName()) &&
                    (main.getItemMeta().getDisplayName().equals("§eVotre tombe") ||
                            off.getItemMeta().getDisplayName().equals("§eVotre tombe"))) {
                player.setCompassTarget(PlayerManager.PLAYER_DEATH_LOCATION.get(player));
            }
        } else {
            player.setCompassTarget(player.getWorld().getSpawnLocation());
        }
    }
}
