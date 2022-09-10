package me.badgraphixd.expansionproject.menu.menus;

import me.badgraphixd.expansionproject.corpse.Corpse;
import me.badgraphixd.expansionproject.corpse.CorpseItem;
import me.badgraphixd.expansionproject.menu.MenuInstance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CorpseLootMenuInstance extends MenuInstance {

    private final Corpse corpse;

    public CorpseLootMenuInstance(Corpse corpse) {
        this.corpse = corpse;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        System.out.println("Clicked: " + slot);

        if (slot < corpse.getLootItems().size()) {
            CorpseItem clickedItem = corpse.getLootItems().get(slot);

            System.out.println("Clicked in range");

            // todo: check if has required tool in inventory
            if (new Random().nextDouble() >= clickedItem.getFragility()) {
                event.getWhoClicked().getInventory().addItem(clickedItem.getItem());
            }
            corpse.getLootItems().remove(slot);

            if (corpse.isEmpty()) {
                event.getWhoClicked().closeInventory();
            }
            else {
                refreshInventory(event.getClickedInventory(), corpse);
            }
        }

        event.setCancelled(true);
    }

    @Override
    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (corpse.getLootItems().size() / 9 + 1) * 9, name);
        refreshInventory(inventory, corpse);
        player.openInventory(inventory);
    }

    private void refreshInventory(Inventory inventory, Corpse corpse) {
        inventory.setContents(corpse.getLootItems().stream().map(CorpseItem::getItem).map(ItemStack::clone).toArray(ItemStack[]::new));
    }
}
