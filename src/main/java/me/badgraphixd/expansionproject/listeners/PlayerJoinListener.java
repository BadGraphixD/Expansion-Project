package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.managers.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        PlayerDataManager.loadPlayerData(event.getPlayer());
    }

}
