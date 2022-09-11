package me.badgraphixd.expansionproject.skill;

import me.badgraphixd.expansionproject.ExpansionProject;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class SkillSet {

    private final Map<Skill, SkillInstance> skillInstances = new HashMap<>();

    public SkillSet(Document document) {
        for (ChildSkill skill : ChildSkill.values()) {
            skillInstances.put(skill, new ChildSkillInstance(this, skill, (Document) document.get(skill.name())));
        }
        for (ParentSkill skill : ParentSkill.values()) {
            skillInstances.put(skill, new ParentSkillInstance(this, skill, (Document) document.get(skill.name())));
        }
    }

    public Document toDocument() {
        Document document = new Document();
        for (SkillInstance skillInstance : skillInstances.values()) {

            String name = "invalid";
            Skill skill = skillInstance.skill;

            if (skill instanceof ChildSkill) name = ((ChildSkill) skill).name();
            else if (skill instanceof ParentSkill) name = ((ParentSkill) skill).name();
            else ExpansionProject.error("Tried to save invalid skill: " + skill.toString());

            document.append(name, skillInstance.toDocument());
        }
        return document;
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
