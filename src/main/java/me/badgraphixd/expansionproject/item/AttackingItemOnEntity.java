package me.badgraphixd.expansionproject.item;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface AttackingItemOnEntity {
    void attackOnEntity(EntityDamageByEntityEvent event);
}
