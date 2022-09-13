package me.badgraphixd.expansionproject.item;

import de.tr7zw.nbtapi.NBTItem;
import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.magic.spell.ItemSpell;
import me.badgraphixd.expansionproject.magic.spell.Spell;
import me.badgraphixd.expansionproject.managers.SpellManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellCastingItem extends CustomItem implements
        AttackingItemInAir, AttackingItemOnBlock, AttackingItemOnEntity,
        UsableItemInAir, UsableItemOnBlock, UsableItemOnEntity {

    public enum CastingType {
        ATTACK,
        USE,
        ATTACK_AND_USE
    }

    private static final String SPELLS_NBT_KEY = "Spells";

    private final CastingType castingType;

    public SpellCastingItem(Material material, int customModelData, CastingType castingType) {
        super(material, customModelData);
        this.castingType = castingType;
    }

    @Override
    public void attackInAir(PlayerInteractEvent event) {
        if (castingType == CastingType.ATTACK || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(event.getItem())) {
                if (spell.isInAir()) {
                    spell.castInAir(event);
                }
            }
        }
    }

    @Override
    public void attackOnBlock(PlayerInteractEvent event) {
        if (castingType == CastingType.ATTACK || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(event.getItem())) {
                if (spell.isOnBlock()) {
                    spell.castOnBlock(event);
                }
            }
        }
    }

    @Override
    public void attackOnEntity(EntityDamageByEntityEvent event) {
        if (castingType == CastingType.ATTACK || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(((Player) event.getDamager()).getInventory().getItemInMainHand())) {
                if (spell.isOnEntity()) {
                    spell.castOnEntity(event);
                }
            }
        }
    }

    @Override
    public void useInAir(PlayerInteractEvent event) {
        if (castingType == CastingType.USE || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(event.getItem())) {
                if (spell.isInAir()) {
                    spell.castInAir(event);
                }
            }
        }
    }

    @Override
    public void useOnBlock(PlayerInteractEvent event) {
        if (castingType == CastingType.USE || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(event.getItem())) {
                if (spell.isOnBlock()) {
                    spell.castOnBlock(event);
                }
            }
        }
    }

    @Override
    public void useOnEntity(PlayerInteractEntityEvent event) {
        if (castingType == CastingType.USE || castingType == CastingType.ATTACK_AND_USE) {
            for (ItemSpell spell : getAttachedSpells(event.getPlayer().getInventory().getItem(event.getHand()))) {
                if (spell.isOnEntity()) {
                    spell.castOnEntity(event);
                }
            }
        }
    }

    public static ItemStack attachSpells(ItemStack item, String... spells) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.getStringList(SPELLS_NBT_KEY).addAll(Arrays.asList(spells));
        return nbtItem.getItem();
    }

    public static ItemStack attachSpells(ItemStack item, ItemSpell... spells) {
        return attachSpells(item, Arrays.stream(spells).map(ItemSpell::getName).toArray(String[]::new));
    }

    private static List<ItemSpell> getAttachedSpells(ItemStack item) {
        List<ItemSpell> spells = new ArrayList<>();

        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey(SPELLS_NBT_KEY)) {

            for (String spellName : nbtItem.getStringList(SPELLS_NBT_KEY)) {

                Spell spell = SpellManager.getSpell(spellName);
                if (spell == null) {
                    ExpansionProject.error("Attached spell does not exist: " + spellName);
                    continue;
                }
                if (!(spell instanceof ItemSpell)) {
                    ExpansionProject.error("Spell attached to item is no ItemSpell: " + spellName);
                    continue;
                }

                spells.add((ItemSpell) spell);
            }
        }

        return spells;
    }

}
