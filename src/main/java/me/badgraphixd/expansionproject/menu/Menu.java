package me.badgraphixd.expansionproject.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class Menu {

    protected String name;

    public String getName() {
        return name;
    }

    public void open(Player player) {
        player.openInventory(createInventory());
    }

    public abstract void onClick(InventoryClickEvent event);
    public abstract Inventory createInventory();

}
