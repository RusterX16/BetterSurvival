package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public record AsyncPlayerChatListener(Main main) implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onAsyncPlayerChat(@NotNull AsyncPlayerChatEvent e) {
        String format = "§f%1$s_§7»_%2$s";
        e.setFormat(format.replace("_", " "));
    }
}
