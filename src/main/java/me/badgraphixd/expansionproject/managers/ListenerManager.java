package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.listeners.EntityDamageByEntityListener;
import me.badgraphixd.expansionproject.listeners.InventoryClickListener;
import me.badgraphixd.expansionproject.listeners.PlayerInteractEntityListener;
import me.badgraphixd.expansionproject.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public static void init(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new InventoryClickListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
    }

}
