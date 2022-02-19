package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

public record PlayerRespawnListener(Main main) implements Listener {

    @EventHandler
    public void onPlayerRespawn(@NotNull PlayerRespawnEvent e) {
//        e.getPlayer().getInventory().addItem(new ItemBuilder(Material.COMPASS).displayName("§eVotre tombe").build());
    }
}
