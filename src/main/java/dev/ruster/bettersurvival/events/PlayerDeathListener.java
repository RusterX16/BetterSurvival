package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import dev.ruster.bettersurvival.entities.PlayerManager;
import dev.ruster.bettersurvival.io.IOManager;
import dev.ruster.bettersurvival.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
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
        double x = Math.round(location.getX());
        double y = Math.round(location.getY());
        double z = Math.round(location.getZ());

        new GraveStone(player, new Location(location.getWorld(), x, y, z));
        player.getInventory().addItem(new ItemBuilder(Material.COMPASS).displayName("§eVotre tombe").build());
        player.sendMessage("§eUne tombe a été créée en §a" + x + " " + y + " " + z);
        IOManager.write("plugins/players.json", player.getName() + " died in " +
                x + " " + y + " " + z + " in world \"" + player.getWorld().getName() + "\"");
        PlayerManager.PLAYER_DEATH_LOCATION.put(player, location);

        e.getDrops().clear();
        e.setDroppedExp(0);
    }
}
