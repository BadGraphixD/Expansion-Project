package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.corpse.Corpse;
import me.badgraphixd.expansionproject.managers.CorpseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class PlayerArmorStandManipulateListener implements Listener {

    @EventHandler
    private void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {

        Corpse clickedCorpse = CorpseManager.find(event.getRightClicked());
        if (clickedCorpse != null) {
            clickedCorpse.loot(event.getPlayer());
            event.setCancelled(true);
        }

    }

}
