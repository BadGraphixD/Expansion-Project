package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.item.*;
import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    private void onEntityInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        CustomItemIdentifier id = CustomItemIdentifier.fromItem(item);

        if (id != null) {
            CustomItem customItem = ItemManager.getCustomItemWithId(id);

            if (customItem != null) {

                Action action = event.getAction();

                if (action.equals(Action.LEFT_CLICK_BLOCK) && customItem instanceof AttackingItemOnBlock) {
                    ((AttackingItemOnBlock) customItem).attackOnBlock(event);
                }
                else if (action.equals(Action.RIGHT_CLICK_BLOCK) && customItem instanceof UsableItemOnBlock) {
                    ((UsableItemOnBlock) customItem).useOnBlock(event);
                }
                else if (action.equals(Action.LEFT_CLICK_AIR) && customItem instanceof AttackingItemInAir) {
                    ((AttackingItemInAir) customItem).attackInAir(event);
                }
                else if (action.equals(Action.RIGHT_CLICK_AIR) && customItem instanceof UsableItemInAir) {
                    ((UsableItemInAir) customItem).useInAir(event);
                }
            }
        }
    }
}
