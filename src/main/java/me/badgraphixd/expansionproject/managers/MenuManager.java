package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private static Map<String, Menu> menuRegistry = new HashMap();

    public static <T extends Menu> T register(T menu) {
        String name = menu.getName();
        if (menuRegistry.containsKey(name)) {
            ExpansionProject.error("Registering duplicate menu name: \"" + name + "\"");
            return null;
        }
        menuRegistry.put(name, menu);
        return menu;
    }

    public static Menu getMenuWithName(String name) {
        return menuRegistry.get(name);
    }

}
