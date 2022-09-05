package me.badgraphixd.expansionproject;

import org.bukkit.plugin.java.JavaPlugin;

public final class ExpansionProject extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static <T> void message(T msg) { System.out.println("[Expansion Project] " + msg); }
    public static <T> void warn(T warn) { System.out.println("[Expansion Project] WARN: " + warn); }
    public static <T> void error(T error) { System.out.println("[Expansion Project] ERROR: " + error); }

}
