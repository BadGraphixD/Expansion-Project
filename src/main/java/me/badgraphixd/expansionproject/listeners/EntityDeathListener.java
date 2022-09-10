package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.corpse.Corpse;
import me.badgraphixd.expansionproject.managers.CorpseManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    private void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player) && !entity.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            CorpseManager.add(Corpse.fromEntity(entity, event.getDrops()));
            event.getDrops().clear();
        }
    }

}
