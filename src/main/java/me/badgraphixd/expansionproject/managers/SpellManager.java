package me.badgraphixd.expansionproject.managers;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.magic.spell.Spell;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class SpellManager {

    private static final Map<NamespacedKey, Spell> spellRegistry = new HashMap<>();

    public static void register(Spell spell) {
        String name = spell.getName();
        NamespacedKey key = ExpansionProject.createKey(name);
        if (spellRegistry.containsKey(key)) {
            ExpansionProject.error("Registering duplicate spell with key: \"" + name + "\"");
            return;
        }
        spellRegistry.put(key, spell);
    }

    public static Spell getSpell(String name) {
        return spellRegistry.get(ExpansionProject.createKey(name));
    }

}
