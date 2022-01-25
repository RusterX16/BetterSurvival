package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.event.Listener;

public class BlockBreakListener implements Listener {

    private final Main main;

    public BlockBreakListener(Main main) {
        this.main = main;
    }
}
