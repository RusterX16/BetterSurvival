package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import dev.ruster.bettersurvival.entities.SurvivalPlayer;
import dev.ruster.bettersurvival.utils.GUI;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public record PlayerInteractListener(Main main) implements Listener {

    @EventHandler
    public void onPlayerInteract(@NotNull PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        Action action = e.getAction();
        ItemStack item = player.getItemInHand();
        SurvivalPlayer sp = SurvivalPlayer.getPlayerFromBukkitPlayer(player);

        if(block != null && block.getType() == Material.SKELETON_SKULL) {
            AtomicReference<GraveStone> atomic = new AtomicReference<>();
            sp.getGraveStones().stream()
                    .filter(gs -> Utils.isSameLocation(gs.getLocation(), block.getLocation()))
                    .forEach(atomic::set);

            if(action == Action.RIGHT_CLICK_BLOCK) {
                if(atomic.get() != null) {
                    if(player.isSneaking()) {
                        GUI gui = atomic.get().getGUI();
                        ItemStack[] content = gui.getInventory().getContents();
                        IntStream.range(0, 36)
                                .filter(i -> content[i] != null && content[i].getType() != Material.AIR)
                                .forEach(i -> sp.set(i, content[i]));
                        sp.helmet(gui.get(40));
                        sp.chestplate(gui.get(39));
                        sp.leggings(gui.get(38));
                        sp.boots(gui.get(37));
                        player.getInventory().setItemInOffHand(gui.get(41));
                        sp.level(atomic.get().getLevel());
                        sp.exp(atomic.get().getExp());
                        gui.clear();
                        block.setType(Material.AIR);
                        Bukkit.getOnlinePlayers().forEach(p -> p.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, 1F, 1F));
                    } else {
//                        atomic.get().getGUI().open(sp);
                    }
                }
            }
        }

        if((action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) && item.getType() == Material.EXPERIENCE_BOTTLE) {
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().startsWith("§aExpérience perdue")) {
                AtomicReference<GraveStone> atomic = new AtomicReference<>();
                sp.getGraveStones().stream()
                        .filter(gs -> Utils.isSameLocation(gs.getLocation(), block.getLocation()))
                        .forEach(atomic::set);

                sp.exp(atomic.get().getExp());
                sp.level(atomic.get().getLevel());
                item.setType(Material.AIR);
            }
        }
    }
}
