package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerChatListener implements Listener {

    private final Main main;

    public AsyncPlayerChatListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onAsyncPlayerChat(@NotNull AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();
        String format = "§f$p §8» $m";

        e.setFormat(format.replace("$p", player.getName()).replace("$m", message));
        e.setMessage(message);
    }
}
