package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public static void init(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EntityDeathListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new InventoryClickListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerArmorStandManipulateListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), plugin);
    }

}
