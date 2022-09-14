package me.badgraphixd.expansionproject;

import me.badgraphixd.expansionproject.crafting.ExactMaterialChoice;
import me.badgraphixd.expansionproject.item.CustomItem;
import me.badgraphixd.expansionproject.item.SpellCastingItem;
import me.badgraphixd.expansionproject.magic.Effect;
import me.badgraphixd.expansionproject.magic.EffectInvocation;
import me.badgraphixd.expansionproject.magic.mana.ManaType;
import me.badgraphixd.expansionproject.magic.spell.ItemSpell;
import me.badgraphixd.expansionproject.managers.CraftingManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.projectiles.ProjectileSource;

public class Testing {

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
            super(Material.STICK, 1000, true, CastingType.ATTACK_AND_USE);
        }
    }

    public static void init() {

        ItemSpell testSpell = (ItemSpell) new ItemSpell("test_spell", ManaType.FIRE, 100, ItemSpell.ON_EVERYTHING)
                .addEffects(new TestEffect(true, 0f, 1f));
        TestSpellCastingItem wand = new TestSpellCastingItem();
        wand.addName("Wand").addGlow();

        CustomItem netherAlloy = new CustomItem(Material.NETHER_BRICK, 1000, true).addName("Nether Alloy").addGlow();

        ShapedRecipe netherAlloyRecipe = new ShapedRecipe(ExpansionProject.createKey("nether_alloy"), netherAlloy.getItem())
                .shape(" N ", "NGN", " N ")
                .setIngredient('N', new ExactMaterialChoice(Material.NETHER_BRICK))
                .setIngredient('G', new ExactMaterialChoice(Material.GOLD_INGOT));

        ShapedRecipe wandRecipe = new ShapedRecipe(ExpansionProject.createKey("wand"), wand.getItem())
                .shape("N", "S", "S")
                .setIngredient('N', new RecipeChoice.ExactChoice(netherAlloy.getItem()))
                .setIngredient('S', new ExactMaterialChoice(Material.STICK));

        CraftingManager.register(netherAlloyRecipe);
        CraftingManager.register(wandRecipe);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(SpellCastingItem.attachSpells(wand.getItem(), testSpell));
            player.getInventory().addItem(netherAlloy.getItem());
        }
    }

}
