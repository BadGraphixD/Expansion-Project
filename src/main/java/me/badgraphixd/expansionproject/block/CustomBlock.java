package me.badgraphixd.expansionproject.block;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class CustomBlock {

    private final double hardness;

    public CustomBlock(float hardness) {
        this.hardness = hardness;
    }

    public abstract List<ItemStack> getDrops();

    public double getHardness() {
        return hardness;
    }

}
