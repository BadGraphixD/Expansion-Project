package me.badgraphixd.expansionproject.skill;

public class SkillInstance {

    private final Skill skill;
    private float value;

    public SkillInstance(Skill skill, float value) {
        this.skill = skill;
        this.value = value;
    }

    public Skill getSkill() {
        return skill;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
