package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerQuitListener implements Listener {

    private final Main main;

    public PlayerQuitListener(Main main) {
        this.main = main;
    }

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
