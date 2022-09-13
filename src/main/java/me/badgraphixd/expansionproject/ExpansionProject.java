package me.badgraphixd.expansionproject;

import me.badgraphixd.expansionproject.item.SpellCastingItem;
import me.badgraphixd.expansionproject.magic.Effect;
import me.badgraphixd.expansionproject.magic.EffectInvocation;
import me.badgraphixd.expansionproject.magic.mana.ManaType;
import me.badgraphixd.expansionproject.magic.spell.ItemSpell;
import me.badgraphixd.expansionproject.managers.CorpseManager;
import me.badgraphixd.expansionproject.managers.DatabaseManager;
import me.badgraphixd.expansionproject.managers.ListenerManager;
import me.badgraphixd.expansionproject.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;

public final class ExpansionProject extends JavaPlugin {

    private static class TestEffect extends Effect {

        public TestEffect(boolean positiveEffect, float baseProbability, float relativeProbability) {
            super(positiveEffect, baseProbability, relativeProbability);
        }

        @Override
        public void invoke(EffectInvocation invocation) {
            if (invocation.invoker instanceof ProjectileSource) {
                ((ProjectileSource) invocation.invoker).launchProjectile(Fireball.class);
            }
        }

    }

    private static class TestSpellCastingItem extends SpellCastingItem {
        public TestSpellCastingItem() {
            super(Material.STICK, 1000, CastingType.ATTACK_AND_USE);
        }
    }

    private static ExpansionProject instance;

    private int tickTask;

    @Override
    public void onEnable() {

        instance = this;

        DatabaseManager.init(this);
        PlayerDataManager.init(this);
        ListenerManager.init(this);

        if (!Bukkit.getScheduler().isCurrentlyRunning(tickTask)) {
            tickTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                CorpseManager.tick();
                PlayerDataManager.tick();
            }, 0, 1);
        }

        message("Plugin started!");


        // Testing
        ItemSpell testSpell = (ItemSpell) new ItemSpell("test_spell", ManaType.FIRE, 100,
            ItemSpell.IN_AIR | ItemSpell.ON_BLOCK | ItemSpell.ON_ENTITY)
            .addEffects(new TestEffect(true, 0f, 1f));
        TestSpellCastingItem wand = new TestSpellCastingItem();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(SpellCastingItem.attachSpells(wand.getItem(), testSpell));
        }

    }

    @Override
    public void onDisable() {

    }

    public static ExpansionProject getInstance() { return instance; }
    public static NamespacedKey createKey(String name) { return new NamespacedKey(instance, name); }

    public static <T> void message(T msg) { System.out.println("[Expansion-Project] " + msg); }
    public static <T> void warn(T warn) { System.out.println("[Expansion-Project] WARN: " + warn); }
    public static <T> void error(T error) { System.out.println("[Expansion-Project] ERROR: " + error); }

}
