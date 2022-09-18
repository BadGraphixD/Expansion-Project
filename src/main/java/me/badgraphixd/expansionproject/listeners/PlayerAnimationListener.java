package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.managers.BlockManager;
import me.badgraphixd.expansionproject.managers.BrokenBlockManager;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

public class PlayerAnimationListener implements Listener {

    @EventHandler
    private void onPlayerAnimation(PlayerAnimationEvent event) {

        // todo: migrate this to PacketManager and use packets instead of events
        // works for now

        Player player = event.getPlayer();
        Block targetBlock = player.getTargetBlockExact(5, FluidCollisionMode.NEVER);
        if (targetBlock == null || !BlockManager.isCustomBlock(targetBlock)) return;

        Location blockLocation = targetBlock.getLocation();
        if (!BrokenBlockManager.isBrokenBlock(blockLocation)) return;

        BrokenBlockManager.getBrokenBlock(blockLocation).incrementDamage(player, 1);
    }

}
