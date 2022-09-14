package me.badgraphixd.expansionproject.magic.spell;

import me.badgraphixd.expansionproject.magic.EffectInvocation;
import me.badgraphixd.expansionproject.magic.mana.ManaType;
import me.badgraphixd.expansionproject.managers.PlayerDataManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemSpell extends Spell {

    public static final int IN_AIR = 1;
    public static final int ON_BLOCK = 1 << 1;
    public static final int ON_ENTITY = 1 << 2;

    public static final int ON_EVERYTHING = IN_AIR | ON_BLOCK | ON_ENTITY;

    protected final int flags;

    public ItemSpell(String name, ManaType manaType, int manaCost, int flags) {
        super(name, manaType, manaCost);
        this.flags = flags;
    }

    public void castInAir(PlayerInteractEvent event) {
        cast(new EffectInvocation(event.getPlayer(), event.getPlayer().getLocation(), null),
                PlayerDataManager.getPlayerData(event.getPlayer()));
    }

    public void castOnBlock(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null) {
            cast(new EffectInvocation(event.getPlayer(), clickedBlock.getLocation(), null),
                    PlayerDataManager.getPlayerData(event.getPlayer()));
        }
    }

    public void castOnEntity(EntityDamageByEntityEvent event) {
        cast(new EffectInvocation(event.getDamager(), event.getEntity().getLocation(), event.getEntity()),
                PlayerDataManager.getPlayerData((Player) event.getDamager()));
    }

    public void castOnEntity(PlayerInteractEntityEvent event) {
        cast(new EffectInvocation(event.getPlayer(), event.getRightClicked().getLocation(), event.getRightClicked()),
                PlayerDataManager.getPlayerData(event.getPlayer()));
    }

    public boolean isInAir() {
        return (flags & IN_AIR) == IN_AIR;
    }

    public boolean isOnBlock() {
        return (flags & ON_BLOCK) == ON_BLOCK;
    }

    public boolean isOnEntity() {
        return (flags & ON_ENTITY) == ON_ENTITY;
    }

}
