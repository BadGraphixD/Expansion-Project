package me.badgraphixd.expansionproject.skill;

import org.bson.Document;

import java.util.Random;

public class ChildSkillInstance extends SkillInstance<ChildSkill> {

    private final int LEVEL_EXPERIENCE = 1000;
    private final double LEVEL_EXPERIENCE_EXPONENT = 1.025;
    private final double LEVEL_SPREAD_PROBABILITY = 0.05;

    private int experience;

    public ChildSkillInstance(SkillSet set, ChildSkill skill) {
        this(set, skill, 0, 0);
    }

    public ChildSkillInstance(SkillSet set, ChildSkill skill, int level, int experience) {
        super(set, skill, level);
        this.experience = experience;
    }

    public ChildSkillInstance(SkillSet set, ChildSkill skill, Document document) {
        this(set, skill, document.getInteger("level"), document.getInteger("experience"));
    }

    @Override
    public Document toDocument() {
        return new Document()
            .append("level", level)
            .append("experience", experience);
    }

    public void addExperience(int experience) {
        this.experience += experience;
        handleLevelUp();
    }

    public void addLevels(int level, boolean spread) {
        this.level += level;
        if (spread) {
            for (int i = 0; i < level; i++) {
                trySpreadSkill();
            }
        }
        ((ParentSkillInstance) set.getSkillInstance(skill.getParentSkill())).updateLevel();
    }

    private void handleLevelUp() {
        int requiredExperience = (int)(LEVEL_EXPERIENCE * Math.pow(LEVEL_EXPERIENCE_EXPONENT, level));
        if (experience >= requiredExperience) {
            experience -= requiredExperience;
            addLevels(1, true);
        }
    }

    private void trySpreadSkill() {
        for (Skill relatedSkill : skill.getRelatedSkills()) {
            if (relatedSkill != skill && new Random().nextDouble() < LEVEL_SPREAD_PROBABILITY) {
                ((ChildSkillInstance) set.getSkillInstances().get(relatedSkill)).addLevels(1, false);
            }
        }
    }

}
