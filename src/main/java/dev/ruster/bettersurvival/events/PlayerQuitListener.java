package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public record PlayerQuitListener(Main main) implements Listener {

    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String message = Utils.LOG_IN_OUT_MESSAGE
                .replace("*", "§c-")
                .replace("$p", "§e" + player.getName())
                .replace("$c", "§c" + Bukkit.getOnlinePlayers().size())
                .replace("$m", "§e" + Bukkit.getMaxPlayers());
        e.setQuitMessage(message);
    }
}
