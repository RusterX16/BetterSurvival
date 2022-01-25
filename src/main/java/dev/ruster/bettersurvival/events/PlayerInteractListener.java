package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class PlayerInteractListener implements Listener {

    private final Main main;

    public PlayerInteractListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        Action action = e.getAction();

        if(block != null && block.getType() == Material.SKELETON_SKULL) {
            if(action == Action.RIGHT_CLICK_BLOCK) {
                for(Map.Entry<GraveStone, Player> entry : main.getPlayersGraveStones().entrySet()) {
                    GraveStone gs = entry.getKey();
                    Player p = entry.getValue();

                    if(player == p) {
                        gs.getGUI().open();
                        break;
                    }
                }
            } else if(action == Action.LEFT_CLICK_BLOCK && player.isSneaking()) {

            }
        }
    }
}
