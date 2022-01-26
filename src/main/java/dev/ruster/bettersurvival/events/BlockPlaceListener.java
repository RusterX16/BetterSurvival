package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public record BlockPlaceListener(Main main) implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if(block.getType() == Material.SPAWNER) {
            CreatureSpawner cs = (CreatureSpawner) block.getState();
            ItemStack item = player.getItemInHand();
            ItemMeta meta = item.getItemMeta();

            if(!meta.hasLore()) {
                return;
            }

            String name = meta.getLore().toString().replace("[", "").replace("]", "");
            cs.setCreatureTypeByName(name);
        }
    }
}
