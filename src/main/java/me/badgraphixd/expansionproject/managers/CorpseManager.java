package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.corpse.Corpse;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class CorpseManager {

    private static final List<Corpse> corpseList = new ArrayList<>();

    public static void add(Corpse corpse) {
        corpseList.add(corpse);
    }

    public static void tick() {
        corpseList.removeIf(corpse -> corpse.checkDespawn());
    }

    public static Corpse find(Entity entity) {
        for (Corpse corpse : corpseList) {
            if (corpse.getEntity().equals(entity)) {
                return corpse;
            }
        }
        return null;
    }

}
