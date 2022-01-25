package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDeathListener implements Listener {

    private final Main main;

    public PlayerDeathListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location location = player.getLocation();
        GraveStone gs = new GraveStone(player);

        location.getBlock().setType(Material.SKELETON_SKULL);

        e.getDrops().clear();
        e.setDroppedExp(0);
    }
}
