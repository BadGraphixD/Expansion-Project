package me.badgraphixd.expansionproject.item;

import org.bukkit.event.player.PlayerInteractEntityEvent;

public interface UsableItemOnEntity {
    void useOnEntity(PlayerInteractEntityEvent event);
}
