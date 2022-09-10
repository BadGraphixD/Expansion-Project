package me.badgraphixd.expansionproject.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class MenuInstance {
    protected String name;

    public abstract void onClick(InventoryClickEvent event);
    public abstract void open(Player player);

    public void setName(String name) { this.name = name; }
}
