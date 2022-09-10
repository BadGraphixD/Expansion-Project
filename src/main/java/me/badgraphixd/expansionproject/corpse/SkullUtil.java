package me.badgraphixd.expansionproject.corpse;

import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SkullUtil {

    public static ItemStack getSkullItem(Player player) {
        return SkullCreator.itemFromUuid(player.getUniqueId());
    }

    public static ItemStack getSkullItem(Entity entity) {
        String base64;

        switch (entity.getType()) {
            case ZOMBIE:
                return new ItemStack(Material.ZOMBIE_HEAD);
            case CREEPER:
                return new ItemStack(Material.CREEPER_HEAD);
            case SKELETON:
                return new ItemStack(Material.SKELETON_SKULL);
            case WITHER_SKELETON:
                return new ItemStack(Material.WITHER_SKELETON_SKULL);
            case BLAZE:
                base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ==";
                break;
            case CAVE_SPIDER:
                base64 = "1234567890";
                break;
            case CHICKEN:
                base64 = "1234567890";
                break;
            case COW:
                base64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19";
                break;
            case ENDERMAN:
                base64 = "1234567890";
                break;
            case PIG:
                base64 = "1234567890";
                break;
            case SHEEP:
                base64 = "1234567890";
                break;
            case SLIME:
                base64 = "1234567890";
                break;
            case SPIDER:
                base64 = "1234567890";
                break;
            default:
                return null;
        }

        return SkullCreator.itemFromBase64(base64);
    }

}
