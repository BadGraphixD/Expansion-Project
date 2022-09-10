package me.badgraphixd.expansionproject.corpse;

import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenu;
import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenuInstance;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Corpse {

    private static final CorpseLootMenu corpseLootMenu = new CorpseLootMenu();

    private final ArmorStand entity;
    private final List<CorpseItem> lootItems;

    public Corpse(Location location, ItemStack skullItem, List<CorpseItem> lootItems) {
        this.entity = location.getWorld().spawn(location.clone().subtract(0, 1.5, 0), ArmorStand.class);
        entity.setVisible(false);
        entity.setInvulnerable(true);
        entity.setGravity(false);
        entity.setCollidable(false);
        entity.setCanPickupItems(false);
        entity.getEquipment().setHelmet(skullItem);

        Random rand = new Random();
        final float rotationRange = .5f;
        EulerAngle headRotation = new EulerAngle(
                rand.nextDouble(-rotationRange, rotationRange),
                rand.nextDouble(-rotationRange, rotationRange),
                rand.nextDouble(-rotationRange, rotationRange)
        );
        entity.setHeadPose(headRotation);

        this.lootItems = lootItems;
    }

    public Corpse(Player player) {
        this(player.getLocation(), getSkullItem(player),
                Arrays.stream(player.getInventory().getContents())
                        .filter(Objects::nonNull).map(ItemStack::clone).map(CorpseItem::new).collect(Collectors.toList())
        );
    }

    public boolean checkDespawn() {
        if (entity == null || !entity.isValid() || entity.isDead()) {
            return true;
        }
        if (lootItems.isEmpty()) {
            entity.remove();
            return true;
        }
        return false;
    }

    public void loot(Player player) {
        corpseLootMenu.openInstance(player, new CorpseLootMenuInstance(this));
    }

    public boolean isEmpty() { return lootItems.isEmpty(); }

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

