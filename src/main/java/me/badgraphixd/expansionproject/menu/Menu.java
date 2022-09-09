package me.badgraphixd.expansionproject.menu;

import me.badgraphixd.expansionproject.managers.MenuManager;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class Menu {

    protected String name;

    public Menu(String name) {
        this.name = name;
        MenuManager.register(this);
    }

    public String getName() {
        return name;
    }
    public abstract void onClick(InventoryClickEvent event);

}
