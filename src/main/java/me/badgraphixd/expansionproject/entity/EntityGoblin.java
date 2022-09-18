package me.badgraphixd.expansionproject.entity;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntityPillager;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.entity.Pillager;

public class EntityGoblin extends EntityPillager {

    public EntityGoblin(Location location) {
        super(EntityTypes.at, ((CraftWorld) location.getWorld()).getHandle());

        this.b(location.getX(), location.getY(), location.getZ());
        Pillager entity = (Pillager) this.getBukkitEntity();
        entity.setSilent(true);
        entity.setCustomNameVisible(false);
        entity.setCustomName("Goblin");

        System.out.println("Spawned entity goblin!");
    }

    @Override
    public void u() {
        this.bS.a(0, new PathfinderGoalMeleeAttack(this, 1.0, true));
        this.bS.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true, true));
        this.bS.a(2, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.bS.a(2, new PathfinderGoalRandomLookaround(this));
    }

}
