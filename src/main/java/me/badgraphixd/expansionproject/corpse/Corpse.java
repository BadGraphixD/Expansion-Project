package me.badgraphixd.expansionproject.corpse;

import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenu;
import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenuInstance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Corpse {

    private final ArmorStand entity;
    private final List<CorpseItem> lootItems;

    public Corpse(Location location, ItemStack skullItem, List<CorpseItem> lootItems) {
        this.entity = location.getWorld().spawn(location, ArmorStand.class);
        entity.setVisible(false);
        entity.setInvulnerable(true);
        entity.setGravity(false);
        entity.setCollidable(false);
        entity.getEquipment().setHelmet(skullItem);
        this.lootItems = lootItems;
    }

    public Corpse(Player player) {
        this(player.getLocation(), getSkullItem(player), Arrays.stream(player.getInventory().getContents()).map(CorpseItem::new).collect(Collectors.toList()));
    }

    public boolean checkDespawn() {
        return false;
    }

    public void loot(Player player) {
        CorpseLootMenu.openInstance(player, new CorpseLootMenuInstance(this));
    }

    public ArmorStand getEntity() {
        return entity;
    }

    public List<CorpseItem> getLootItems() {
        return lootItems;
    }

    private static ItemStack getSkullItem(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwnerProfile(player.getPlayerProfile());
        item.setItemMeta(meta);
        return item;
    }

}

