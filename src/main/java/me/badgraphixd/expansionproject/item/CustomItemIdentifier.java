package me.badgraphixd.expansionproject.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CustomItemIdentifier {
    public Material material;
    public int customModelId;

    public CustomItemIdentifier(Material material, int customModelId) {
        this.material = material;
        this.customModelId = customModelId;
    }

    public static CustomItemIdentifier fromItem(ItemStack item) {
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasCustomModelData()) return null;
        return new CustomItemIdentifier(item.getType(), item.getItemMeta().getCustomModelData());
    }

    public String toString() {
        return "[" + material.toString() + "|" + customModelId + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomItemIdentifier that = (CustomItemIdentifier) o;
        return customModelId == that.customModelId && material == that.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, customModelId);
    }
}