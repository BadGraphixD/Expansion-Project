package me.badgraphixd.expansionproject.block;

import org.bukkit.block.Block;

public class PlayerBlockBreakingProcess {

    private static final int DAMAGE_TIMEOUT_CANCEL_DURATION = 100;

    private int animation;
    private final Block block;

    private float damage;
    private final float durability;

    private int ticksWithoutDamage;
    private boolean damagedLastTick;

    public PlayerBlockBreakingProcess(Block block, float durability) {
        this.block = block;
        this.durability = durability;

        this.animation = -1;
        this.damage = 0f;

        this.ticksWithoutDamage = 0;
        this.damagedLastTick = false;
    }

    public void tick() {
        damagedLastTick = false;
        ticksWithoutDamage++;
    }

    public void damage(float damage) {
        damagedLastTick = true;
        ticksWithoutDamage = 0;
        this.damage += damage;
    }

    public boolean updateAnimation() {
        int newAnimation = (int)((damage / durability) * 11f - 1f);
        if (newAnimation > animation) {
            animation = newAnimation;
            return true;
        }
        return false;
    }

    public boolean wasDamagedLastTick() {
        return damagedLastTick;
    }

    public boolean finished() { return damage >= durability; }

    public boolean timeout() {
        return ticksWithoutDamage >= DAMAGE_TIMEOUT_CANCEL_DURATION;
    }

    public int getAnimation() {
        return animation;
    }

    public Block getBlock() {
        return block;
    }

}
