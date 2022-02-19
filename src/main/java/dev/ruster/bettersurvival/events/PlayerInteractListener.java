package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import dev.ruster.bettersurvival.utils.GUI;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public record PlayerInteractListener(Main main) implements Listener {

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        Action action = e.getAction();

        if(block != null && block.getType() == Material.SKELETON_SKULL) {
            AtomicReference<GraveStone> atomic = new AtomicReference<>();
            GraveStone.GRAVE_STONE_LIST.stream()
                    .filter(gs -> Utils.isSameLocation(gs.getLocation(), block.getLocation()))
                    .forEach(atomic::set);

            if(action == Action.RIGHT_CLICK_BLOCK && atomic.get() != null && player.isSneaking()) {
                GUI gui = atomic.get().getGUI();
                ItemStack[] content = gui.getInventory().getContents();
                Arrays.stream(player.getInventory().getContents())
                        .filter(i -> i != null && i.getType() != Material.AIR)
                        .forEach(i -> player.getWorld().dropItemNaturally(player.getLocation(), i));
                IntStream.range(0, 36)
                        .filter(i -> content[i] != null && content[i].getType() != Material.AIR)
                        .forEach(i -> player.getInventory().setItem(i, content[i]));

                player.getInventory().setHelmet(gui.get(39));
                player.getInventory().setChestplate(gui.get(38));
                player.getInventory().setLeggings(gui.get(37));
                player.getInventory().setBoots(gui.get(36));
                player.getInventory().setItemInOffHand(gui.get(41));

                player.setLevel(player.getLevel() + atomic.get().getLevel());
                player.setExp(Math.min(1, player.getExp() + atomic.get().getExp()));
                gui.clear();

                block.setType(Material.AIR);
                Bukkit.getOnlinePlayers().forEach(p ->
                        p.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 1F, 1F));
                GraveStone.GRAVE_STONE_LIST.remove(atomic.get());
            }
        }
    }
}
