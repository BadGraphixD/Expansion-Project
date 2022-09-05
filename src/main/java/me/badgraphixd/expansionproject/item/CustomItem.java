package me.badgraphixd.expansionproject.item;

import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CustomItem {

    protected ItemStack item;

    public CustomItem(Material material, int customModelData) {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setCustomModelData(customModelData);
        item.setItemMeta(meta);
        ItemManager.register(this);
    }

    public CustomItem addName(String name) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public CustomItem addLore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public CustomItem addGlow() {
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack getItem() {
        return item;
    }

}
