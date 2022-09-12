package me.badgraphixd.expansionproject.magic;

import java.util.Random;

public abstract class Effect {

    public final boolean positiveEffect;
    public final float baseProbability;
    public final float relativeProbability;

    public Effect(boolean positiveEffect, float baseProbability, float relativeProbability) {
        this.positiveEffect = positiveEffect;
        this.baseProbability = baseProbability;
        this.relativeProbability = relativeProbability;
    }

    public boolean tryInvoke(float relativeSkillLevel) {
        if (relativeSkillLevel < 0f) return false;
        relativeSkillLevel = Math.min(1f, relativeSkillLevel);
        return new Random().nextFloat() < getProbability(relativeSkillLevel);
    }

    private float getProbability(float relativeSkillLevel) {
        return baseProbability + relativeProbability * (positiveEffect ? relativeSkillLevel : 1f - relativeSkillLevel);
    }

    public abstract void invoke(EffectInvocation invocation);

}
