package me.badgraphixd.expansionproject.skill;

public class SkillRequirement {

    public final Skill skill;
    public final int minLevel;
    public final int maxLevel;

    public SkillRequirement(Skill skill, int minLevel, int maxLevel) {
        this.skill = skill;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

}
