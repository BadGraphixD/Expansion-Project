package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.managers.MenuManager;
import me.badgraphixd.expansionproject.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {

        Inventory inventory = event.getClickedInventory();
        if (inventory != null && inventory.getHolder() == null) {

            String title = event.getView().getTitle();
            Menu menu = MenuManager.getMenuWithName(title);

            if (menu != null) {
                menu.onClick(event);
            }
        }

    }

}
