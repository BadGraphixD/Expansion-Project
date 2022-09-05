package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.item.AttackingItemOnEntity;
import me.badgraphixd.expansionproject.item.CustomItem;
import me.badgraphixd.expansionproject.item.CustomItemIdentifier;
import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        Entity damager = event.getDamager();
        if (damager instanceof Player) {

            ItemStack item = ((Player) damager).getInventory().getItemInMainHand();
            CustomItemIdentifier id = CustomItemIdentifier.fromItem(item);

            if (id != null) {
                CustomItem customItem = ItemManager.getCustomItemWithId(id);

                if (customItem != null && customItem instanceof AttackingItemOnEntity) {
                    ((AttackingItemOnEntity) customItem).attackOnEntity(event);
                }
            }
        }
    }
}
