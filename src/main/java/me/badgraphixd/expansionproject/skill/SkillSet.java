package me.badgraphixd.expansionproject.skill;

import java.util.HashMap;
import java.util.Map;

public class SkillSet {

    private final Map<Skill, SkillInstance> skillInstances = new HashMap<>();

    public SkillSet() {
        for (ChildSkill skill : ChildSkill.values()) {
            skillInstances.put(skill, new ChildSkillInstance(this, skill));
        }
        for (ParentSkill skill : ParentSkill.values()) {
            skillInstances.put(skill, new ParentSkillInstance(this, skill));
        }
    }

    public Map<Skill, SkillInstance> getSkillInstances() {
        return skillInstances;
    }

    public SkillInstance getSkillInstance(Skill skill) {
        return skillInstances.get(skill);
    }

    public int getLevel(Skill skill) {
        return getSkillInstance(skill).getLevel();
    }

}
