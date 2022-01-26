package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.SurvivalPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public record PlayerDeathListener(Main main) implements Listener {

    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location location = player.getLocation();
        SurvivalPlayer sp = SurvivalPlayer.getPlayerFromBukkitPlayer(player);

        sp.createGraveStone(location);
        e.getDrops().clear();
        e.setDroppedExp(0);
    }
}
