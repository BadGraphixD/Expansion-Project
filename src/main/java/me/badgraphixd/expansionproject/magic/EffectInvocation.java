package me.badgraphixd.expansionproject.magic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EffectInvocation {

    public Entity invoker;
    public Location location;
    public Entity involved;

    public EffectInvocation(Entity invoker, Location location, Entity involved) {
        this.invoker = invoker;
        this.location = location;
        this.involved = involved;
    }
}
