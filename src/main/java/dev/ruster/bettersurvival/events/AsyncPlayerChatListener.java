package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public record AsyncPlayerChatListener(Main main) implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onAsyncPlayerChat(@NotNull AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String format = "§f%1$s §7» %2$s";

        e.setFormat(format.replace("%1$s", player.getName()).replace("%2$s", message));
        e.setMessage(message);
    }
}
