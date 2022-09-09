package me.badgraphixd.expansionproject.menu.menus;

import me.badgraphixd.expansionproject.corpse.Corpse;
import me.badgraphixd.expansionproject.corpse.CorpseItem;
import me.badgraphixd.expansionproject.menu.MenuInstance;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CorpseLootMenuInstance extends MenuInstance {

    private final Corpse corpse;

    public CorpseLootMenuInstance(Corpse corpse) {
        this.corpse = corpse;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        CorpseItem clickedItem = corpse.getLootItems().get(event.getSlot());
    }

    @Override
    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27);
        inventory.setContents(corpse.getLootItems().stream().map(CorpseItem::getItem).toArray(ItemStack[]::new));
    }
}
