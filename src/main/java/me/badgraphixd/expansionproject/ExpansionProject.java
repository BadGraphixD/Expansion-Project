package me.badgraphixd.expansionproject;

import me.badgraphixd.expansionproject.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExpansionProject extends JavaPlugin {

    private static ExpansionProject instance;

    private int tickTask;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        instance = this;

        PacketManager.init(this);
        DatabaseManager.init(this);
        PlayerDataManager.init(this);
        ListenerManager.init(this);

        Testing.init();

        if (!Bukkit.getScheduler().isCurrentlyRunning(tickTask)) {
            tickTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                CorpseManager.tick();
                PlayerDataManager.tick();
                PlayerGUIManager.tick();
            }, 0, 1);
        }

        message("Plugin started!");
    }

    @Override
    public void onDisable() {

    }

    public static NamespacedKey createKey(String name) { return new NamespacedKey(instance, name); }

    public static <T> void message(T msg) { System.out.println("[Expansion-Project] " + msg); }
    public static <T> void warn(T warn) { System.out.println("[Expansion-Project] WARN: " + warn); }
    public static <T> void error(T error) { System.out.println("[Expansion-Project] ERROR: " + error); }

}
