package me.badgraphixd.expansionproject.menu;

import me.badgraphixd.expansionproject.managers.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class Menu {

    protected String name;

    public Menu(String name) {
        this.name = name;
        MenuManager.register(this);
    }

    public String getName() {
        return name;
    }

    public void open(Player player) {
        player.openInventory(createInventory());
    }

    public abstract void onClick(InventoryClickEvent event);
    public abstract Inventory createInventory();

}
