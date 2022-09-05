package me.badgraphixd.expansionproject.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomItemIdentifier {
    public Material material;
    public int customModelId;

    public CustomItemIdentifier(Material material, int customModelId) {
        this.material = material;
        this.customModelId = customModelId;
    }

    public static CustomItemIdentifier fromItem(ItemStack item) {
        if (item == null || !item.hasItemMeta() || item.getItemMeta().hasCustomModelData()) return null;
        return new CustomItemIdentifier(item.getType(), item.getItemMeta().getCustomModelData());
    }

    public String toString() {
        return "[" + material.toString() + "|" + customModelId + "]";
    }
}