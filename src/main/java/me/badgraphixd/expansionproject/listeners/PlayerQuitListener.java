package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.managers.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        PlayerDataManager.setPlayerOffline(event.getPlayer());
    }

}
