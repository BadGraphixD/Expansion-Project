package me.badgraphixd.expansionproject.skill;

import org.bson.Document;

public class ParentSkillInstance extends SkillInstance<ParentSkill> {

    public ParentSkillInstance(SkillSet set, ParentSkill skill) {
        this(set, skill, 0);
    }

    public ParentSkillInstance(SkillSet set, ParentSkill skill, int level) {
        super(set, skill, level);
        updateLevel();
    }

    public ParentSkillInstance(SkillSet set, ParentSkill skill, Document document) {
        super(set, skill, document.getInteger("level"));
    }

    @Override
    public Document toDocument() {
        return new Document()
            .append("level", level);
    }

    public void updateLevel() {
        int summedUpLevels = 0;
        for (Skill childSkill : skill.getChildSkills()) {
            summedUpLevels += set.getLevel(childSkill);
        }
        level = summedUpLevels / skill.getChildSkills().size();
    }

}
