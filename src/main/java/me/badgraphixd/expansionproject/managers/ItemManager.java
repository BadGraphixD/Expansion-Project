package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.item.CustomItem;
import me.badgraphixd.expansionproject.item.CustomItemIdentifier;

import java.util.HashMap;
import java.util.Map;

public class ItemManager {

    private static Map<CustomItemIdentifier, CustomItem> itemRegistry = new HashMap();

    public static <T extends CustomItem> T register(T item) {
        CustomItemIdentifier id = CustomItemIdentifier.fromItem(item.getItem());
        if (id == null) {
            ExpansionProject.error("Registering invalid item");
            return null;
        }
        if (itemRegistry.containsKey(id)) {
            ExpansionProject.error("Registering duplicate item id: " + id);
            return null;
        }
        itemRegistry.put(id, item);
        return item;
    }

    public static CustomItem getCustomItemWithId(CustomItemIdentifier id) {
        return itemRegistry.get(id);
    }

}
