package me.badgraphixd.expansionproject.corpse;

import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenu;
import me.badgraphixd.expansionproject.menu.menus.CorpseLootMenuInstance;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.*;
import java.util.stream.Collectors;

public class Corpse {

    private static final CorpseLootMenu corpseLootMenu = new CorpseLootMenu();

    private final ArmorStand entity;
    private final List<CorpseItem> lootItems;

    private Corpse(Location location, ItemStack skullItem, List<CorpseItem> lootItems) {
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

    public static Corpse fromPlayer(Player player) {
        return new Corpse(player.getLocation(), SkullUtil.getSkullItem(player),
                Arrays.stream(player.getInventory().getContents())
                        .filter(Objects::nonNull).map(ItemStack::clone).map(CorpseItem::new).collect(Collectors.toList())
        );
    }

    public static Corpse fromEntity(Entity entity, List<ItemStack> drops) {
        List<CorpseItem> lootItems = drops.stream().map(ItemStack::clone).map(CorpseItem::new).collect(Collectors.toList());

        if (entity instanceof InventoryHolder) {
            lootItems.addAll(Arrays.stream(((InventoryHolder) entity).getInventory().getContents())
                .filter(Objects::nonNull).map(ItemStack::clone).map(CorpseItem::new).collect(Collectors.toList()));
        }
        return new Corpse(entity.getLocation(), SkullUtil.getSkullItem(entity), lootItems);
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

}

