package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.item.*;
import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        EquipmentSlot hand = event.getHand();
        ItemStack item = event.getPlayer().getInventory().getItem(hand);
        CustomItemIdentifier id = CustomItemIdentifier.fromItem(item);

        if (id != null) {
            CustomItem customItem = ItemManager.getCustomItemWithId(id);

            if (customItem != null && customItem instanceof UsableItemOnEntity) {
                ((UsableItemOnEntity) customItem).useOnEntity(event);
            }
        }
    }
}
