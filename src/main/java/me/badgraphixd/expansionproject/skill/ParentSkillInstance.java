package me.badgraphixd.expansionproject.skill;

public class ParentSkillInstance extends SkillInstance<ParentSkill> {

    public ParentSkillInstance(SkillSet set, ParentSkill skill) {
        super(set, skill, 0);
        updateLevel();
    }

    public void updateLevel() {
        int summedUpLevels = 0;
        for (Skill childSkill : skill.getChildSkills()) {
            summedUpLevels += set.getLevel(childSkill);
        }
        level = summedUpLevels / skill.getChildSkills().size();
    }

}
