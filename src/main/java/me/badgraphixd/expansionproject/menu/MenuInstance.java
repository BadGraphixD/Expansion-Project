package me.badgraphixd.expansionproject.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class MenuInstance {
    public abstract void onClick(InventoryClickEvent event);
    public abstract void open(Player player);
}
