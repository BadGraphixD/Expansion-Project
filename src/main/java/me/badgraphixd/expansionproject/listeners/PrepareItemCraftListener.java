package me.badgraphixd.expansionproject.listeners;

import me.badgraphixd.expansionproject.item.CustomItem;
import me.badgraphixd.expansionproject.item.CustomItemIdentifier;
import me.badgraphixd.expansionproject.managers.ItemManager;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class PrepareItemCraftListener implements Listener {

    @EventHandler
    private void onPrepareItemCraft(PrepareItemCraftEvent event) {

        Recipe recipe = event.getRecipe();
        if (recipe instanceof Keyed) {
            if (((Keyed) recipe).getKey().getNamespace().equals(NamespacedKey.MINECRAFT)) {

                CraftingInventory inventory = event.getInventory();
                for (ItemStack item : inventory.getMatrix()) {

                    CustomItemIdentifier id = CustomItemIdentifier.fromItem(item);
                    if (id != null) {

                        CustomItem customItem = ItemManager.getCustomItemWithId(id);
                        if (customItem != null) {

                            if (customItem.isSeparateItem()) {

                                inventory.setResult(new ItemStack(Material.AIR));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}
