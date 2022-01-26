package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.utils.GUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

public record InventoryCloseListener(Main main) implements Listener {

    @EventHandler
    public void onInventoryClose(@NotNull InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        String title = e.getView().getTitle();
        ItemStack[] content = inventory.getContents();

        if(inventory.getType() == InventoryType.CHEST && inventory.getSize() == 4 * 9) {
            if(title.equals("§8Inventaire de" + player.getName())) {
                GUI copy = new GUI(title, 4);

                IntStream.range(0, inventory.getSize())
                        .filter(i -> content[i] != null && content[i].getType() != Material.AIR)
                        .forEach(i -> copy.set(i, content[i]));

            }
        }
    }
}
