package dev.ruster.bettersurvival;

import dev.ruster.bettersurvival.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenerRegister {

    private ListenerRegister() {
        throw new IllegalStateException("Utility Class");
    }

    public static void register(Main main) {
        registerListener(main);
    }

    private static void registerListener(Main main) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new BlockPlaceListener(main), main);
        pm.registerEvents(new BlockBreakListener(main), main);
        pm.registerEvents(new PlayerJoinListener(main), main);
        pm.registerEvents(new PlayerQuitListener(main), main);
        pm.registerEvents(new PlayerInteractListener(main), main);
        pm.registerEvents(new InventoryCloseListener(main), main);
        pm.registerEvents(new PlayerDeathListener(main), main);
        pm.registerEvents(new AsyncPlayerChatListener(main), main);
    }
}
