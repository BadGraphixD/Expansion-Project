package me.badgraphixd.expansionproject.corpse;

import org.bukkit.inventory.ItemStack;

public class CorpseItem {

    public enum Tool {
        NONE,

        KNIFE,
        SCALPEL
        // add more
    }

    private final ItemStack item;

    private final float chance;
    private final float fragility;
    private final Tool requiredLootingTool;

    public CorpseItem(ItemStack item) {
        this(item, 1f);
    }

    public CorpseItem(ItemStack item, float chance) {
        this(item, chance, 0f);
    }

    public CorpseItem(ItemStack item, float chance, float fragility) {
        this(item, chance, fragility, Tool.NONE);
    }

    public CorpseItem(ItemStack item, float chance, float fragility, Tool requiredLootingTool) {
        this.item = item;
        this.chance = chance;
        this.fragility = fragility;
        this.requiredLootingTool = requiredLootingTool;
    }

    public ItemStack getItem() {
        return item;
    }

    public float getChance() {
        return chance;
    }

    public float getFragility() {
        return fragility;
    }

    public Tool getRequiredLootingTool() {
        return requiredLootingTool;
    }
}
