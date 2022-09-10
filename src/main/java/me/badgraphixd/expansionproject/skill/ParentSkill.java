package me.badgraphixd.expansionproject.skill;

import java.util.Arrays;
import java.util.List;

public enum ParentSkill implements Skill {

    // todo: thoroughly think/design/balance these skills
    // only examples

    FARMING,
    CRAFTING,
    COMBAT,
    SELF_CONTROL,
    ALCHEMY,
    MAGIC,
    ENCHANTING,
    AGILITY,
    MINING(ChildSkill.ROCK_MINING, ChildSkill.WOOD_MINING, ChildSkill.SIEVE_MINING, ChildSkill.DIGGING_MINING);

    private final List<ChildSkill> childSkills;

    ParentSkill(ChildSkill... childSkills) {
        this.childSkills = Arrays.asList(childSkills);
    }

}
