package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.corpse.Corpse;
import me.badgraphixd.expansionproject.managers.CorpseManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            CorpseManager.add(new Corpse(player));
        }
    }
}
