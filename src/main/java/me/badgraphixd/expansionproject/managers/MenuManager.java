package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private static Map<String, Menu> menuRegistry = new HashMap();

    public static void register(Menu menu) {
        String name = menu.getName();
        if (menuRegistry.containsKey(name)) {
            ExpansionProject.error("Registering duplicate menu name: \"" + name + "\"");
            return;
        }
        menuRegistry.put(name, menu);
    }

    public static Menu getMenuWithName(String name) {
        return menuRegistry.get(name);
    }

}
