package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.managers.MenuManager;
import me.badgraphixd.expansionproject.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {

        Inventory inventory = event.getClickedInventory();
        if (inventory != null) { // Clicked inside any inventory

            String title = event.getView().getTitle();
            Menu menu = MenuManager.getMenuWithName(title);

            if (menu != null) { // Upper inventory is menu

                if (inventory.getHolder() == null) { // Clicked menu (upper inventory)
                    menu.onClick(event);
                }
                if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY ||
                    event.getAction() == InventoryAction.COLLECT_TO_CURSOR) { // Moving from/to menu
                    event.setCancelled(true);
                }
            }

        }

    }

}
