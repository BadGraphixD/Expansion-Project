package me.badgraphixd.expansionproject.skill;

public abstract class SkillModifier {
    public final int minLevel;
    public final int maxLevel;

    public SkillModifier(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }
}