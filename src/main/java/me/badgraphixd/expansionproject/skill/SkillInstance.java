package me.badgraphixd.expansionproject.skill;

import org.bson.Document;

public abstract class SkillInstance <T extends Skill> {

    protected final SkillSet set;
    protected final T skill;
    protected int level;

    public SkillInstance(SkillSet set, T skill, int level) {
        this.set = set;
        this.skill = skill;
        this.level = level;
    }

    public SkillSet getSet() {
        return set;
    }

    public T getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public abstract Document toDocument();
}
