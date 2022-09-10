package me.badgraphixd.expansionproject;

import me.badgraphixd.expansionproject.managers.CorpseManager;
import me.badgraphixd.expansionproject.managers.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExpansionProject extends JavaPlugin {

    private int tickTask;

    @Override
    public void onEnable() {

        ListenerManager.init(this);

        if (!Bukkit.getScheduler().isCurrentlyRunning(tickTask)) {
            tickTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                CorpseManager.tick();
            }, 0, 1);
        }

        message("Plugin started!");
    }

    @Override
    public void onDisable() {

    }

    public static <T> void message(T msg) { System.out.println("[Expansion-Project] " + msg); }
    public static <T> void warn(T warn) { System.out.println("[Expansion-Project] WARN: " + warn); }
    public static <T> void error(T error) { System.out.println("[Expansion-Project] ERROR: " + error); }

}
