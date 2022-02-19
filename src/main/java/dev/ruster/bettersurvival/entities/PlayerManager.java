package dev.ruster.bettersurvival.entities;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private PlayerManager() {
        throw new IllegalStateException("Utility Class");
    }

    public static final Map<Player, Location> PLAYER_DEATH_LOCATION = new HashMap<>();
}
