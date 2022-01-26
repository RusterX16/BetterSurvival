package dev.ruster.bettersurvival.events;

import dev.ruster.bettersurvival.Main;
import dev.ruster.bettersurvival.entities.GraveStone;
import dev.ruster.bettersurvival.entities.SurvivalPlayer;
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

public record BlockBreakListener(Main main) implements Listener {

    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        SurvivalPlayer sp = SurvivalPlayer.getPlayerFromBukkitPlayer(player);

        if(block.getType() == Material.SPAWNER) {
            if(player.getItemInHand().getType().name().endsWith("PICKAXE")) {
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
        }

        if(block.getType() == Material.SKELETON_SKULL) {
            if(sp.getGraveStones().stream().anyMatch(gs -> Utils.isSameLocation(gs.getLocation(), block.getLocation()))) {
                GraveStone gs = GraveStone.getByLocation(block.getLocation());

                if(gs != null) {
                    gs.drop(block);
                }
            }
        }
    }
}
