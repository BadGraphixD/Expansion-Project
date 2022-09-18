package me.badgraphixd.expansionproject.skill;

import me.badgraphixd.expansionproject.ExpansionProject;
import me.badgraphixd.expansionproject.role.Profile;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SkillSet {

    private final Map<Skill, SkillInstance<?>> skillInstances = new HashMap<>();

    public SkillSet() {
        for (ChildSkill skill : ChildSkill.values()) {
            skillInstances.put(skill, new ChildSkillInstance(this, skill, 0, 0));
        }
        for (ParentSkill skill : ParentSkill.values()) {
            skillInstances.put(skill, new ParentSkillInstance(this, skill, 0));
        }
    }

    public SkillSet(Profile profile) {
        this();
        Random rand = new Random();

        // Parent skill levels are set to base parent skill levels of race
        for (ParentSkillModifier baseParentSkill : profile.getRace().baseParentSkills) {
            skillInstances.get(baseParentSkill.skill).level = rand.nextInt(baseParentSkill.minLevel, baseParentSkill.maxLevel);
        }
        // Parent skill levels are modified by role
        for (ParentSkillModifier parentSkillModifier : profile.getRole().parentSkillModifiers) {
            skillInstances.get(parentSkillModifier.skill).level = Math.max(0,
                    skillInstances.get(parentSkillModifier.skill).level +
                    rand.nextInt(parentSkillModifier.minLevel, parentSkillModifier.maxLevel)
            );
        }
        // Child skill levels are randomly chosen, so that their average equals to the parent skill level
        for (SkillInstance<?> skillInstance : skillInstances.values()) {
            if (skillInstance instanceof ParentSkillInstance) {
                ParentSkillInstance parentSkillInstance = (ParentSkillInstance) skillInstance;

                int numberOfChildSkills = parentSkillInstance.skill.getChildSkills().size();
                int levelsToDistribute = parentSkillInstance.level * numberOfChildSkills;
                for (int i = 0; i < levelsToDistribute; i++) {
                    int j = rand.nextInt(numberOfChildSkills);
                    for (ChildSkill childSkill : parentSkillInstance.skill.getChildSkills()) {
                        if (j == 0) {
                            skillInstances.get(childSkill).level++;
                            break;
                        }
                        j--;
                    }
                }
            }
        }
        // Child skill level are modified by role
        for (ChildSkillModifier childSkillModifier : profile.getRole().childSkillModifiers) {
            skillInstances.get(childSkillModifier.skill).level = Math.max(0,
                    skillInstances.get(childSkillModifier.skill).level +
                    rand.nextInt(childSkillModifier.minLevel, childSkillModifier.maxLevel)
            );
        }
        // Parent skill levels are updated to be the average of their child skill levels
        for (SkillInstance<?> skillInstance : skillInstances.values()) {
            if (skillInstance instanceof ParentSkillInstance) {
                ((ParentSkillInstance) skillInstance).updateLevel();
            }
        }
    }

    public SkillSet(Document document) {
        this();
        for (ChildSkill skill : ChildSkill.values()) {
            skillInstances.put(skill, new ChildSkillInstance(this, skill, (Document) document.get(skill.name())));
        }
        for (ParentSkill skill : ParentSkill.values()) {
            skillInstances.put(skill, new ParentSkillInstance(this, skill, (Document) document.get(skill.name())));
        }
    }

    public Document toDocument() {
        Document document = new Document();
        for (SkillInstance<?> skillInstance : skillInstances.values()) {

            String name = "invalid";
            Skill skill = skillInstance.skill;

            if (skill instanceof ChildSkill) name = ((ChildSkill) skill).name();
            else if (skill instanceof ParentSkill) name = ((ParentSkill) skill).name();
            else ExpansionProject.error("Tried to save invalid skill: " + skill.toString());

            document.append(name, skillInstance.toDocument());
        }
        return document;
    }

    public Map<Skill, SkillInstance<?>> getSkillInstances() {
        return skillInstances;
    }

    public SkillInstance<?> getSkillInstance(Skill skill) {
        return skillInstances.get(skill);
    }

    public int getLevel(Skill skill) {
        return getSkillInstance(skill).level;
    }

}
