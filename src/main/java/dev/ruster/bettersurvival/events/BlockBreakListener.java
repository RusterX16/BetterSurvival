package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import dev.ruster.bettersurvival.entities.PlayerManager;
import dev.ruster.bettersurvival.utils.GUI;
import dev.ruster.bettersurvival.utils.ItemBuilder;
import dev.ruster.bettersurvival.utils.Utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public record BlockBreakListener(Main main) implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if(block.getType() == Material.SPAWNER && player.getItemInHand().getType().name().endsWith("PICKAXE")) {
            ItemStack pickaxe = player.getItemInHand();

            if(pickaxe.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                CreatureSpawner cs = (CreatureSpawner) block.getState();
                block.getWorld().dropItemNaturally(block.getLocation(),
                        new ItemBuilder(Material.SPAWNER)
                                .lore("§5" + Utils.firstLetterInCapital(cs.getSpawnedType() + " spawner"))
                                .spawnType(cs)
                                .build());
                e.setExpToDrop(0);
            }
        }

        if(block.getType() == Material.SKELETON_SKULL) {
            AtomicReference<GraveStone> atomic = new AtomicReference<>();
            GraveStone.GRAVE_STONE_LIST.stream()
                    .filter(gs -> Utils.isSameLocation(gs.getLocation(), block.getLocation()))
                    .forEach(atomic::set);

            if(atomic.get() == null) {
                return;
            }
            GUI gui = atomic.get().getGUI();
            ItemStack[] content = gui.getInventory().getContents();
            IntStream.range(0, 42)
                    .filter(i -> content[i] != null && content[i].getType() != Material.AIR)
                    .forEach(i -> player.getWorld().dropItemNaturally(block.getLocation(), content[i]));

            player.setLevel(player.getLevel() + atomic.get().getLevel());
            player.setExp(Math.min(1, player.getExp() + atomic.get().getExp()));
            gui.clear();

            e.setDropItems(false);
            block.setType(Material.AIR);
            GraveStone.GRAVE_STONE_LIST.remove(atomic.get());
            PlayerManager.PLAYER_DEATH_LOCATION.remove(player, PlayerManager.PLAYER_DEATH_LOCATION.get(player));
        }
    }
}
