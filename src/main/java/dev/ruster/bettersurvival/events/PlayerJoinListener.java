package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public record PlayerJoinListener(Main main) implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent e) {
        Player player = e.getPlayer();

        String message = Utils.LOG_IN_OUT_MESSAGE
                .replace("*", "§a+")
                .replace("$p", "§e" + player.getName())
                .replace("$c", "§a" + Bukkit.getOnlinePlayers().size())
                .replace("$m", "§e" + Bukkit.getMaxPlayers());
        e.setJoinMessage(message);
    }
}
