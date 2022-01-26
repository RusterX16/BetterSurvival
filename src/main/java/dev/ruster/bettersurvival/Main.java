package dev.ruster.bettersurvival;

import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main extends JavaPlugin {

    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        LOGGER.info("Plugin Enabled");

        ListenerRegister.register(this);
    }

    @Override
    public void onDisable() {
        LOGGER.info("Plugin Disabled");
    }
}
