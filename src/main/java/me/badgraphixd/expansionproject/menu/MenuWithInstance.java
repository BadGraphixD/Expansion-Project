package me.badgraphixd.expansionproject.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuWithInstance extends Menu {

    private static final Map<Player, MenuInstance> playerInstanceMap = new HashMap<>();

    public MenuWithInstance(String name) {
        super(name);
    }

    public void openInstance(Player player, MenuInstance instance) {
        playerInstanceMap.put(player, instance);
        instance.setName(name);
        instance.open(player);
    }

    @Override
    public final void onClick(InventoryClickEvent event) {
        MenuInstance instance = playerInstanceMap.get((Player) event.getWhoClicked());
        if (instance != null) {
            instance.onClick(event);
        }
    }

}
