package me.badgraphixd.expansionproject.skill;

public class ChildSkillModifier extends SkillModifier {
    public final ChildSkill skill;

    public ChildSkillModifier(ChildSkill skill, int minLevel, int maxLevel) {
        super(minLevel, maxLevel);
        this.skill = skill;
    }
}