package me.badgraphixd.expansionproject.skill;

public class ParentSkillModifier extends SkillModifier {
    public final ParentSkill skill;

    public ParentSkillModifier(ParentSkill skill, int minLevel, int maxLevel) {
        super(minLevel, maxLevel);
        this.skill = skill;
    }
}